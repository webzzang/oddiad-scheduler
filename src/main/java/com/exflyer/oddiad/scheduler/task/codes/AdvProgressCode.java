package com.exflyer.oddiad.scheduler.task.codes;

import lombok.Getter;

@Getter
public enum AdvProgressCode {
  RESERVATION("PGT001", "PGT", "신청"),
  PAY_DONE("PGT002", "PGT", "결제 완료"),
  PAY_FAIL("PGT003", "PGT", "결제 실패"),
  PAY_CANCEL("PGT004", "PGT", "결제 취소"),
  RESERVATION_CANCEL("PGT005", "PGT", "신청 취소");

  private String code;

  private String groupCode;

  private String name;

  AdvProgressCode(String code, String groupCode, String name) {
    this.code = code;
    this.groupCode = groupCode;
    this.name = name;
  }
}
