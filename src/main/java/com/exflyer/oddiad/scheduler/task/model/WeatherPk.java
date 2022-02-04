package com.exflyer.oddiad.scheduler.task.model;

import com.exflyer.oddiad.scheduler.share.LocalDateUtils;
import com.exflyer.oddiad.scheduler.task.dto.WeatherData;
import com.exflyer.oddiad.scheduler.task.dto.WeatherHeader;
import com.exflyer.oddiad.scheduler.task.dto.WeatherResponse;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.repository.query.parser.Part;

/**
 * 기기 날씨
 */
@Data
@Embeddable
@NoArgsConstructor
public class WeatherPk implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 측정 X좌표
   */
  @Column(name = "grid_x")
  private String gridX;

  /**
   * 측정 Y좌표
   */
  @Column(name = "grid_y")
  private String gridY;

  /**
   * 예보 날짜
   */
  @Column(name = "fcst_date")
  private String fcstDate;

  /**
   * 예보 시간
   */
  @Column(name = "fcst_time")
  private String fcstTime;

  public WeatherPk(WeatherData weatherData, Partner partner) {
    LocalDateTime now = LocalDateUtils.korNowDateTime();
    this.gridY = String.valueOf(partner.getGridY());
    this.gridX = String.valueOf(partner.getGridX());
    this.fcstDate = now.plusDays(weatherData.getDay()).format(DateTimeFormatter.ofPattern(LocalDateUtils.YYYYMMDD));
    this.fcstTime = StringUtils.leftPad(String.valueOf(weatherData.getHour()), 2, "0");
  }
}
