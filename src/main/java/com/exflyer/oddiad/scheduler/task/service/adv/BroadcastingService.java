package com.exflyer.oddiad.scheduler.task.service.adv;

import com.exflyer.oddiad.scheduler.share.LocalDateUtils;
import com.exflyer.oddiad.scheduler.task.dto.BroadcastingDevice;
import com.exflyer.oddiad.scheduler.task.dto.ExpoBanner;
import com.exflyer.oddiad.scheduler.task.model.BannerBroadcasting;
import com.exflyer.oddiad.scheduler.task.model.Broadcasting;
import com.exflyer.oddiad.scheduler.task.model.BroadcastingFile;
import com.exflyer.oddiad.scheduler.task.model.Files;
import com.exflyer.oddiad.scheduler.task.repository.BannerBroadcastingRepository;
import com.exflyer.oddiad.scheduler.task.repository.BannerMapper;
import com.exflyer.oddiad.scheduler.task.repository.BroadcastingFileRepository;
import com.exflyer.oddiad.scheduler.task.repository.BroadcastingMapper;
import com.exflyer.oddiad.scheduler.task.repository.BroadcastingRepository;
import com.exflyer.oddiad.scheduler.task.repository.FilesRepository;
import io.micrometer.core.instrument.util.StringUtils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Component
@Slf4j
public class BroadcastingService {


  @Autowired
  private BroadcastingRepository broadcastingRepository;

  @Autowired
  private BroadcastingMapper broadcastingMapper;

  @Autowired
  private BroadcastingFileRepository broadcastingFileRepository;

  @Autowired
  private FilesRepository filesRepository;

  @Autowired
  private BannerMapper bannerMapper;

  @Autowired
  private BannerBroadcastingRepository bannerBroadcastingRepository;


  public void addAdv(LocalDateTime nowDate) {

    addDefaultBroadcasting(nowDate, null);
    addBroadcasting(nowDate);
  }

  public void addBannerBroadCasting() {
    bannerBroadcastingRepository.deleteAll();
    bannerBroadcastingRepository.flush();
    List<ExpoBanner> expoBannerList = bannerMapper.findExpoBanner(LocalDateUtils.korNowDateTimeYYYYMMDD());
    List<BannerBroadcasting> bannerBroadcastingList = new ArrayList<>();
    for (ExpoBanner expoBanner : expoBannerList) {
      bannerBroadcastingList.add(new BannerBroadcasting(expoBanner));
    }
    bannerBroadcastingRepository.saveAll(bannerBroadcastingList);
  }

  public void addDefaultBroadcasting(LocalDateTime nowDate, List<BroadcastingDevice> broadcastingDeviceList) {
    // 기본 광고 추가
    List<Files> defaultAdvFiles = filesRepository.findAllDefaultAdv();
    if (CollectionUtils.isEmpty(defaultAdvFiles)) {
      return;
    }
    List<BroadcastingDevice> defaultAdvDevice = broadcastingDeviceList;
    if (CollectionUtils.isEmpty(broadcastingDeviceList)) {
      defaultAdvDevice = broadcastingMapper.findDefaultAdvDevice();
    }
    for (BroadcastingDevice broadcastingDevice : defaultAdvDevice) {
      Broadcasting defaultBroadcasting;
      Optional<Broadcasting> byDeviceIdAndAdvSeq = broadcastingRepository
        .findByDeviceIdAndAdvSeq(broadcastingDevice.getDeviceId(), 0L);
      boolean isImage = defaultAdvFiles.get(0).getContentType().contains("image");
      if (byDeviceIdAndAdvSeq.isPresent()) {
        defaultBroadcasting = byDeviceIdAndAdvSeq.get();
        if (isImage) {
          defaultBroadcasting.setContentsType("image");
        } else {
          defaultBroadcasting.setContentsType("video");
        }
        defaultBroadcasting.setModDate(nowDate);
      } else {
        defaultBroadcasting = new Broadcasting(broadcastingDevice, nowDate, isImage);
      }
      broadcastingRepository.save(defaultBroadcasting);
      for (Files defaultAdvFile : defaultAdvFiles) {
        BroadcastingFile broadcastingFile = new BroadcastingFile(defaultBroadcasting, defaultAdvFile, nowDate);
        if (StringUtils.isNotBlank(broadcastingFile.getPath())) {
          broadcastingFileRepository.save(broadcastingFile);
        }
      }
    }
  }

  public void deleteOldBroadcasting(LocalDateTime nowDate) {

    broadcastingRepository.deleteByLesThanBroadcastingModDate(nowDate.minusMinutes(2L));
  }

  public void deleteBroadcastingFile(){
    broadcastingFileRepository.deleteAll();
  }

  @Transactional
  public List<BroadcastingDevice> addBroadcasting(LocalDateTime nowDate) {
    String targetDayString = nowDate.format(DateTimeFormatter.ofPattern(LocalDateUtils.YYYYMMDD));
    List<BroadcastingDevice> broadcastingDeviceList = broadcastingMapper
      .findBroadcastingDevice(targetDayString, null, null);
    addBroadCasting(nowDate, broadcastingDeviceList);
    return broadcastingDeviceList;
  }

  public void deleteAllBannerBroadCasting() {
    bannerBroadcastingRepository.deleteAll();
  }

  public List<String> addBroadcastingByNewMappingDevice() {
    LocalDateTime nowDate = LocalDateUtils.korNowDateTime();
    String targetDayString = nowDate.format(DateTimeFormatter.ofPattern(LocalDateUtils.YYYYMMDD));
    List<BroadcastingDevice> defaultAdList = broadcastingMapper.findBroadcastingNewMappingDeviceForDefaultAd();
    if (CollectionUtils.isEmpty(defaultAdList)) {
      return new ArrayList<>();
    }
    addDefaultBroadcasting(nowDate, defaultAdList);

    for (BroadcastingDevice broadcastingDevice : defaultAdList) {
      List<BroadcastingDevice> partnerAdList = broadcastingMapper.findBroadcastingDevice(
        targetDayString,
        broadcastingDevice.getPartnerSeq(),
        broadcastingDevice.getDeviceId());
      addBroadCasting(nowDate, partnerAdList);
    }

    return defaultAdList.stream().map(d -> d.getDeviceId()).distinct().collect(Collectors.toList());
  }

  private void addBroadCasting(LocalDateTime nowDate, List<BroadcastingDevice> broadcastingDeviceList) {
    int orderingCount = 1;
    for (BroadcastingDevice broadcastingDevice : broadcastingDeviceList) {
      Optional<Broadcasting> byDeviceIdAndAdvSeq = broadcastingRepository
        .findByDeviceIdAndAdvSeq(broadcastingDevice.getDeviceId(), broadcastingDevice.getAdvSeq());
      orderingCount++;
      Broadcasting broadcasting = byDeviceIdAndAdvSeq.orElse(new Broadcasting());
      broadcasting.setInfoByBroadcastingDevice(broadcastingDevice, orderingCount, nowDate);
      broadcastingRepository.save(broadcasting);
      List<BroadcastingFile> broadcastingFileList = new ArrayList<>();
      List<Files> filesList = filesRepository.findByAdvSeq(broadcasting.getAdvSeq());
      for (Files files : filesList) {
        BroadcastingFile broadcastingFile = new BroadcastingFile(broadcasting, files, nowDate);
        if (StringUtils.isNotBlank(broadcastingFile.getPath())) {
          broadcastingFileList.add(new BroadcastingFile(broadcasting, files, nowDate));
        }
      }
      broadcastingFileRepository.saveAll(broadcastingFileList);
    }
  }
}
