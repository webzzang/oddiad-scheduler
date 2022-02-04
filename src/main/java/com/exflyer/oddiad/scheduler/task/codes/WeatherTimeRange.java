package com.exflyer.oddiad.scheduler.task.codes;

import lombok.Getter;

@Getter
public enum WeatherTimeRange {

  FIRST_TIME("03", 3),
  SECOND("06", 6),
  THIRD("09", 9),
  FOURTH("12", 12),
  FIFTH("15", 15),
  SIXTH("18", 18),
  SEVENTH("21", 21),
  EIGHTH("24", 24);

  WeatherTimeRange(String fcstTime, Integer timeInt) {
    this.fcstTime = fcstTime;
    this.fcstTimeInt = timeInt;
  }

  private String fcstTime;

  private Integer fcstTimeInt;

  public static WeatherTimeRange calcFcstTime(String time) {
    Integer searchTimeInt = Integer.parseInt(time);
    WeatherTimeRange timeRange = WeatherTimeRange.FIRST_TIME;
    for (WeatherTimeRange value : WeatherTimeRange.values()) {

      if (searchTimeInt == value.fcstTimeInt) {
        timeRange = value;
      }

      if (searchTimeInt > value.fcstTimeInt) {
        timeRange = value;
      }
    }
    return timeRange;
  }
}
