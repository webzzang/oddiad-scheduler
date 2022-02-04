package com.exflyer.oddiad.scheduler.task.codes;

import lombok.Getter;

@Getter
public enum TargetGroupCode {
  ALL_MEMBER("NTC001", "NTC", "전체 회원"),
  ALL_ADVERTISERS("NTC002", "NTC", "전체 광고주"),
  ALL_ODDI_ADVERTISERS("NTC003", "NTC", "전체 오디존 광고주"),
  ALL_SUBWAY_ADVERTISERS("NTC004", "NTC", "전체 지하철 광고주"),
  ODDI_ADVERTISERS("NTC005", "NTC", "오디존 광고주"),
  SUBWAY_ADVERTISERS("NTC006", "NTC", "지하철 광고주"),
  MEMBER("NTC007", "NTC", "회원 검색"),
  PHONE_NUMBER("NTC008", "NTC", "전화번호 입력")
  ;

  private String code;

  private String groupCode;

  private String name;

  TargetGroupCode(String code, String groupCode, String name) {
    this.code = code;
    this.groupCode = groupCode;
    this.name = name;
  }

  public static TargetGroupCode findByCode(String templateId) {
    for (TargetGroupCode code : TargetGroupCode.values()) {

      if (templateId.equalsIgnoreCase(code.getCode())) {
        return code;
      }
    }
    return null;
  }
}
