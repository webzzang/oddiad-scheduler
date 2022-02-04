package com.exflyer.oddiad.scheduler.task.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

@Data
@NoArgsConstructor
public class KakaoCoordinates {

  private Double latitude;

  private Double longitude;

  private String hCode;

  public KakaoCoordinates(KakaoLocalResult localResult) {
    if (localResult != null && !CollectionUtils.isEmpty(localResult.getDocuments())) {
      if (localResult.getDocuments().get(0).getAddressType() != null &&
        localResult.getDocuments().get(0).getAddressType().equalsIgnoreCase("ROAD")) {
        this.latitude = Double.valueOf(localResult.getDocuments().get(0).getY());
        this.longitude = Double.valueOf(localResult.getDocuments().get(0).getX());
      } else {
        this.latitude = Double.valueOf(localResult.getDocuments().get(0).getY());
        this.longitude = Double.valueOf(localResult.getDocuments().get(0).getX());
        this.hCode = localResult.getDocuments().get(0).getAddress().getHCode();
      }
    }
  }
}
