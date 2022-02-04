package com.exflyer.oddiad.scheduler.task.codes;

import lombok.Getter;

@Getter
public enum WeatherCodes {

  FINE("WTR001", "맑음", "ico_w01"),
  A_LITTLE_OF_CLOUDS("WTR002", "구름조금", "ico_w02"),
  A_LOT_OF_CLOUDS("WTR003", "구름많음", "ico_w03"),
  CLOUDS("WTR004", "흐림", "ico_w04"),
  RAIN("WTR005", "비", "ico_w05"),
  SNOW("WTR006", "눈", "ico_w06")
  ;

  @Getter
  private String code;

  @Getter
  private String name;

  @Getter
  private String icon;

  WeatherCodes(String code, String name, String icon) {
    this.code = code;
    this.name = name;
    this.icon = icon;
  }
}
