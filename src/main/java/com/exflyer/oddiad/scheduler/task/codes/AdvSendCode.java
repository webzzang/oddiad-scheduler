package com.exflyer.oddiad.scheduler.task.codes;

import lombok.Getter;

@Getter
public enum AdvSendCode {

  READY("ADM001", "ADM", "대기"),
  ACCEPT("ADM002", "ADM", "승인 발송"),
  DENY("ADM003", "ADM", "보류 발송"),
  WILL_EXPIRED("ADM004", "ADM", "종료 예정 발송");

  private String code;

  private String groupCode;

  private String name;

  AdvSendCode(String code, String groupCode, String name) {
    this.code = code;
    this.groupCode = groupCode;
    this.name = name;
  }
}
