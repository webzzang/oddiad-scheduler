package com.exflyer.oddiad.scheduler.task.jobs.adv;

import com.exflyer.oddiad.scheduler.share.LocalDateUtils;
import com.exflyer.oddiad.scheduler.task.codes.TaskScheduleTime;
import com.exflyer.oddiad.scheduler.task.service.adv.BroadcastingService;
import com.exflyer.oddiad.scheduler.task.service.device.DevicePushService;
import com.exflyer.oddiad.scheduler.task.service.weather.DeviceWeatherService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Slf4j
public class BroadcastingTask {

  @Autowired
  private BroadcastingService broadcastingService;

  @Autowired
  private DevicePushService devicePushService;

  @Autowired
  private DeviceWeatherService deviceWeatherService;


  /**
   * ### 당일 광고 편성
   *
   * - 목적 : 기존 광고 방영 중인 광고를 삭제 하고 금일 부터 방영 할 광고를 추가 한다.
   *
   * - 조건
   *   - 오디존 파트너(PTT001)
   *   - 승인(ADT002)
   *   - 결제완료(PGT002) 상태
   * - 테이블
   *   - broadcating
   *   - banner_broadcasting
   * - 주기 :  매일 03 시
   * - Task Class : BroadcastingTask.java
   */
//  @Scheduled(cron = "${schedule.adv.broadcasting}")
  @Scheduled(fixedDelay = 10 * TaskScheduleTime.MIN)
  public void adEditing(){
    StopWatch stopWatch = new StopWatch("##### 광고 편성");
    stopWatch.start();
    LocalDateTime nowDate = LocalDateUtils.korNowDateTime();
    broadcastingService.deleteBroadcastingFile();
    broadcastingService.addAdv(nowDate);
    broadcastingService.deleteOldBroadcasting(nowDate);
    devicePushService.changeBroadcasting();
    stopWatch.stop();
    log.info("{}", stopWatch.shortSummary());
  }
  
  @Scheduled(fixedDelay = TaskScheduleTime.HOUR)
  public void addBannerBroadCasting(){
    StopWatch stopWatch = new StopWatch("##### 배너 편성");
    stopWatch.start();
    broadcastingService.deleteAllBannerBroadCasting();
    broadcastingService.addBannerBroadCasting();
    stopWatch.stop();
    log.info("{}", stopWatch.shortSummary());
  }

  @Scheduled(fixedDelay = TaskScheduleTime.MIN)
  public void addNewPartnerMappingDevice(){
    StopWatch stopWatch = new StopWatch("##### 신규 디바이스 매핑");
    stopWatch.start();
    List<String> deviceIdList = broadcastingService.addBroadcastingByNewMappingDevice();
    for (String deviceId : deviceIdList) {
      deviceWeatherService.update(deviceId);
    }
    devicePushService.pushNewMappingDevice(deviceIdList);

    stopWatch.stop();
    log.info("{} mapping device count : {}", stopWatch.shortSummary(), deviceIdList.size());
  }

}
