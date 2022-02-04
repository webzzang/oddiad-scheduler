package com.exflyer.oddiad.scheduler.task.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 결제
 */
@Data
@Entity
@Table(name = "payment")
public class Payment implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 순번
   */
  @Id
  @Column(name = "seq", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seq;

  /**
   * 종류(결제, 취소)
   */
  @Column(name = "type", nullable = false)
  private String type;

  /**
   * 회원 id
   */
  @Column(name = "member_id", nullable = false)
  private String memberId;

  /**
   * 광고 순번
   */
  @Column(name = "adv_seq", nullable = false)
  private Long advSeq;

  /**
   * 금액
   */
  @Column(name = "price", nullable = false)
  private BigDecimal price;

  /**
   * 채널 종류(오디존, 지하철)
   */
  @Column(name = "channel_type", nullable = false)
  private String channelType;

  /**
   * 상품 이름
   */
  @Column(name = "product_name", nullable = false)
  private String productName;

  /**
   * 광고 이름
   */
  @Column(name = "adv_name", nullable = false)
  private String advName;

  /**
   * 광고 시작 날짜
   */
  @Column(name = "adv_start_date", nullable = false)
  private String advStartDate;

  /**
   * 광고 종료 날짜
   */
  @Column(name = "adv_end_date", nullable = false)
  private String advEndDate;

  /**
   * 생성 날짜
   */
  @Column(name = "reg_date", nullable = false)
  private Date regDate;

  /**
   * 응답 코드(PG 연동)
   */
  @Column(name = "response_code")
  private String responseCode;

  /**
   * 응답 메세지(PG 연동)
   */
  @Column(name = "response_message")
  private String responseMessage;

  /**
   * 연동 날짜(PG 연동)
   */
  @Column(name = "sync_date")
  private Date syncDate;

  /**
   * 성공 여부(PG 연동)
   */
  @Column(name = "success")
  private Integer success;

}
