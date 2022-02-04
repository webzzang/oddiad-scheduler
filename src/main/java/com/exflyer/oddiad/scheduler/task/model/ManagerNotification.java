package com.exflyer.oddiad.scheduler.task.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 알림
 */
@Data
@Entity
@Table(name = "notification")
@NoArgsConstructor
public class ManagerNotification implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 순번
   */
  @Id
  @Column(name = "role_seq", nullable = false)
  private Long roleSeq;

  /**
   * 기기상태 알림코드
   */
  @Column(name = "device_state_code")
  private Long deviceStateCode;

  /**
   * 기기상태 알림 수신여부
   */
  @Column(name = "device_push")
  private boolean devicePush;

  /**
   * 푸시여부
   */
  @Column(name = "manager_push")
  private boolean managerPush;

  /**
   * 생성날짜
   */
  @Column(name = "reg_date")
  private String regDate;

}
