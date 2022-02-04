package com.exflyer.oddiad.scheduler.task.service.geography;

import com.exflyer.oddiad.scheduler.task.dto.GridConvertResult;
import com.exflyer.oddiad.scheduler.task.dto.KakaoCoordinates;
import com.exflyer.oddiad.scheduler.task.model.Partner;
import com.exflyer.oddiad.scheduler.task.repository.PartnerRepository;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeographyService {

  @Autowired
  private KakaoLocalService kakaoLocalService;

  @Autowired
  private PartnerRepository partnerRepository;


  @Autowired
  private GridConverter gridConverter;

  public void updateGeographyInfo() {
    List<Partner> partnerList = partnerRepository.findAll();
    for (Partner partner : partnerList) {
      if (StringUtils.isNotBlank(partner.getOldAddr())) {
        KakaoCoordinates coordinatesByAddress = kakaoLocalService.findCoordinatesByAddress(partner.getOldAddr());
        GridConvertResult convertResult = gridConverter
          .convert(coordinatesByAddress.getLatitude(), coordinatesByAddress.getLongitude());
        partner.setLatitude(convertResult.getLat());
        partner.setLongitude(convertResult.getLng());
        partner.setGridX(convertResult.getX());
        partner.setGridY(convertResult.getY());
      }
    }
    partnerRepository.saveAll(partnerList);
  }

}
