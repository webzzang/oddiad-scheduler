package com.exflyer.oddiad.scheduler.task.dto;

import lombok.Data;

@Data
public class BroadcastingDevice {

  private Long advSeq;

  private Long partnerSeq;

  private String deviceId;

  private String displayDiv;

  private String sideContentsType;

  private String bottomContentsType;

  private Integer slotVideoTime;

  private String contentsType;
}
