package com.exflyer.oddiad.scheduler.task.codes;

import lombok.Getter;

@Getter
public enum AdvAuditCode {
  READY("ADT001", "ADT", "대기"),
  ACCEPT("ADT002", "ADT", "승인"),
  DENY("ADT003", "ADT", "보류"),
  PAY_DONE("ADT004", "ADT", "결제완료");

  private String code;

  private String groupCode;

  private String name;

  AdvAuditCode(String code, String groupCode, String name) {
    this.code = code;
    this.groupCode = groupCode;
    this.name = name;
  }
}
