package com.exflyer.oddiad.scheduler.task.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;

/**
 * 기기 날씨
 */
@Data
@Entity
@Table(name = "device_weather")
public class DeviceWeather implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 장비 id
   */
  @Id
  @Column(name = "device_id", nullable = false)
  private String deviceId;

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
   * 미세먼지 농도
   */
  @Column(name = "pm10")
  private String pm10;

  /**
   * 초미세먼지 농도
   */
  @Column(name = "pm25")
  private String pm25;

  /**
   * 미세먼지 등급
   */
  @Column(name = "pm10_grade")
  private String pm10Grade;

  /**
   * 초미세먼지 등급
   */
  @Column(name = "pm25_grade")
  private String pm25Grade;

  /**
   * 주소
   */
  @Column(name = "addr")
  private String addr;


  @Column(name = "icon")
  private String icon;

  @Transient
  private String originAddr;

  @Transient
  private String sido;

}
