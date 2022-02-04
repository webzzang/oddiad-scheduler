package com.exflyer.oddiad.scheduler.task.codes;

import lombok.Getter;

@Getter
public enum DustGradeCodes {

  FINE("DUS001", "맑음", 1),
  NORMAL("DUS002", "보통", 2),
  BAD("DUS003", "나쁨", 3),
  VERY_BADE("DUS004", "매우나쁨", 4);

  @Getter
  private String code;

  @Getter
  private String name;

  @Getter
  private Integer value;

  DustGradeCodes(String code, String name, Integer value) {
    this.code = code;
    this.name = name;
    this.value = value;
  }

  public static DustGradeCodes findByGradeValue(Integer value) {
    for (DustGradeCodes dustGradeCodes : DustGradeCodes.values()) {
      if (dustGradeCodes.value == value) {
        return dustGradeCodes;
      }
    }
    return null;
  }
}
