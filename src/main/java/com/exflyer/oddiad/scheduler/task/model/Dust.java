package com.exflyer.oddiad.scheduler.task.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 미세먼지
 */
@Data
@Entity
@Table(name = "dust")
public class Dust implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 구군 이름
   */
  @Id
  @Column(name = "si_name", nullable = false)
  private String siName;

  /**
   * pm10
   */
  @Column(name = "pm10", nullable = false)
  private String pm10;

  /**
   * pm10_grade
   */
  @Column(name = "pm10_grade", nullable = false)
  private String pm10Grade;

  /**
   * pm25
   */
  @Column(name = "pm25", nullable = false)
  private String pm25;

  /**
   * pm25_grade
   */
  @Column(name = "pm25_grade", nullable = false)
  private String pm25Grade;

}
