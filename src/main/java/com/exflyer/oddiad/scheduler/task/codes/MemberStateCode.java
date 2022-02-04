package com.exflyer.oddiad.scheduler.task.codes;

import lombok.Getter;

public enum MemberStateCode {

  USING("CTS001", "사용"),
  STOP("CTS002", "정지"),
  PASSWORD_FAIL("CTS003", "비밀번호오류"),
  SLEEP("CTS004", "휴면"),
  ADMIN_USING("MSC001", "사용");

  @Getter
  private String code;

  @Getter
  private String name;

  MemberStateCode(String code, String name) {
    this.code = code;
    this.name = name;
  }
}
