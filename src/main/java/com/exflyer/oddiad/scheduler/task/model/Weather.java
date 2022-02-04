package com.exflyer.oddiad.scheduler.task.model;

import com.exflyer.oddiad.scheduler.task.codes.WeatherCodes;
import com.exflyer.oddiad.scheduler.task.dto.WeatherData;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 기기 날씨
 */
@Data
@Entity
@Table(name = "weather")
@NoArgsConstructor
@DynamicUpdate
public class Weather implements Serializable {

  private static final long serialVersionUID = 1L;

  @EmbeddedId
  WeatherPk weatherPk;

  /**
   * 하늘 상태
   */
  @Column(name = "sky")
  private String sky;

  /**
   * 기온
   */
  @Column(name = "tmp")
  private String tmp;

  /**
   * 일 최저 기온
   */
  @Column(name = "tmn")
  private String tmn;

  /**
   * 일 최고 기온
   */
  @Column(name = "tmx")
  private String tmx;

  /**
   * 강수 확률
   */
  @Column(name = "pop")
  private String pop;

  /**
   * 습도
   */
  @Column(name = "reh")
  private String reh;

  /**
   * 풍속
   */
  @Column(name = "wsd")
  private String wsd;

  /**
   * 날씨 아이콘
   */
  @Column(name = "weather_icon")
  private String weatherIcon;

  @Column(name = "weather_code")
  private String weatherCode;


  public Weather(WeatherData weatherData, Partner partner) {

    WeatherPk weatherPk = new WeatherPk(weatherData, partner);
    this.weatherPk = weatherPk;
    this.sky = weatherData.getSky();
    this.tmp = weatherData.getTemp();
    if (!StringUtils.equals(weatherData.getTmn(), "-999.0")) {
      this.tmn = weatherData.getTmn();
    }
    if (StringUtils.isBlank(weatherData.getTmn())) {
      this.tmn = "-";
    }
    if (!StringUtils.equals(weatherData.getTmx(), "-999.0")) {
      this.tmx = weatherData.getTmx();
    }
    if (StringUtils.isBlank(weatherData.getTmx())) {
      this.tmx = "-";
    }
    this.pop = weatherData.getPop();
    this.reh = weatherData.getReh();
    this.wsd = String.format("%.1f", Double.valueOf(weatherData.getWs()));
    if (weatherData.getPty().equals("0")) {
      if (weatherData.getSky().equals("1")) {
        this.weatherCode = WeatherCodes.FINE.getName();
        this.weatherIcon = WeatherCodes.FINE.getIcon();
      } else if (weatherData.getSky().equals("2")) {
        this.weatherCode = WeatherCodes.A_LITTLE_OF_CLOUDS.getName();
        this.weatherIcon = WeatherCodes.A_LITTLE_OF_CLOUDS.getIcon();
      } else if (weatherData.getSky().equals("3")) {
        this.weatherCode = WeatherCodes.A_LOT_OF_CLOUDS.getName();
        this.weatherIcon = WeatherCodes.A_LOT_OF_CLOUDS.getIcon();
      } else {
        this.weatherCode = WeatherCodes.CLOUDS.getName();
        this.weatherIcon = WeatherCodes.CLOUDS.getIcon();
      }
    } else {
      if (weatherData.getPty().equals("1")) {
        this.weatherCode = WeatherCodes.RAIN.getName();
        this.weatherIcon = WeatherCodes.RAIN.getIcon();
      } else {
        this.weatherCode = WeatherCodes.SNOW.getName();
        this.weatherIcon = WeatherCodes.SNOW.getIcon();
      }
    }
  }

  public void setTmxAndTmn(Weather weatherInDb) {
    this.tmx = weatherInDb.getTmx();
    this.tmn = weatherInDb.getTmn();
  }
}
