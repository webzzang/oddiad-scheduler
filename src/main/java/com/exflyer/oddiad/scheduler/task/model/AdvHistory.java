package com.exflyer.oddiad.scheduler.task.model;

import com.exflyer.oddiad.scheduler.share.LocalDateUtils;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 광고내역
 */
@Data
@Entity
@Table(name = "advHistory")
public class AdvHistory implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 순번
   */
  @Id
  @Column(name = "seq", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seq;

  /**
   *  광고 순번
   */
  @Column(name = "adv_seq")
  private Long advSeq;

  /**
   *  파트너 순번
   */
  @Column(name = "partner_seq")
  private Long partnerSeq;

  /**
   *  회원 id
   */
  @Column(name = "member_id")
  private String memberId;

  /**
   *  장비 id
   */
  @Column(name = "device_id")
  private String deviceId;

  /**
   *  장비 이름
   */
  @Column(name = "device_name")
  private String deviceName;

  /**
   *  광고 시작 날짜
   */
  @Column(name = "adv_start_date")
  private String advStartDate;

  /**
   *  광고 종료 날짜
   */
  @Column(name = "adv_end_date")
  private String advEndDate;

  /**
   *  생성 날짜
   */
  @Column(name = "reg_date")
  private LocalDateTime regDate = LocalDateUtils.korNowDateTime();

}
