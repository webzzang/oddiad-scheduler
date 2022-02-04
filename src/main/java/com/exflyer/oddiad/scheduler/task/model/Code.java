package com.exflyer.oddiad.scheduler.task.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 코드
 */
@Data
@Entity
@Table(name = "code")
public class Code implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 코드
   */
  @Id
  @Column(name = "code", nullable = false)
  private String code;

  /**
   * 그룹 코드
   */
  @Column(name = "group_code", nullable = false)
  private String groupCode;

  /**
   * 이름
   */
  @Column(name = "name")
  private String name;

  /**
   * 사용
   */
  @Column(name = "usable")
  private Integer usable;

  /**
   * 정렬
   */
  @Column(name = "ordering")
  private Integer ordering;

  /**
   * 값
   */
  @Column(name = "val")
  private String val;

  /**
   * 사용 타입
   */
  @Column(name = "using_type")
  private String usingType;

}
