package com.exflyer.oddiad.scheduler.task.codes;

import lombok.Getter;

@Getter
public enum ChannelTypeCode {
  ODDI("PTT001", "PTT", "오디존"),
  SUBWAY("PTT002", "PTT", "지하철"),
  PARTNER_REQ("PTT003", "PTT", "파트너신청");

  private String code;

  private String groupCode;

  private String name;

  ChannelTypeCode(String code, String groupCode, String name) {
    this.code = code;
    this.groupCode = groupCode;
    this.name = name;
  }
}
