package com.exflyer.oddiad.scheduler.task.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 광고
 */
@Data
@Entity
@Table(name = "adv")
public class Adv implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 순번
   */
  @Id
  @Column(name = "seq", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seq;

  /**
   * 회원 id
   */
  @Column(name = "member_id")
  private String memberId;

  /**
   * 제목
   */
  @Column(name = "title")
  private String title;

  /**
   * 업종 종류 코드
   */
  @Column(name = "business_type_code")
  private String businessTypeCode;

  /**
   * 시작 날짜
   */
  @Column(name = "start_date")
  private String startDate;

  /**
   * 종료 날짜
   */
  @Column(name = "end_date")
  private String endDate;

  /**
   * 금액
   */
  @Column(name = "price")
  private BigDecimal price;

  /**
   * 할인 금액
   */
  @Column(name = "discount_price")
  private BigDecimal discountPrice;

  /**
   * 수정 날짜
   */
  @Column(name = "reg_date")
  private LocalDateTime regDate;

  /**
   * 변경 날짜
   */
  @Column(name = "mod_date")
  private LocalDateTime modDate;

  /**
   * 심사 코드
   */
  @Column(name = "audit_code")
  private String auditCode;

  /**
   * 진행 코드(심사중, 결제전, 결제후 등)
   */
  @Column(name = "progress_code")
  private String progressCode;

  /**
   * 쿠폰 번호
   */
  @Column(name = "coupon_number")
  private String couponNumber;

  /**
   * 결제 순번
   */
  @Column(name = "payment_seq")
  private Long paymentSeq;

  /**
   * 거절 이유
   */
  @Column(name = "rejection_reason")
  private String rejectionReason;

  /**
   * 거절 코드
   */
  @Column(name = "rejection_code")
  private String rejectionCode;

  /**
   * 거절 날짜
   */
  @Column(name = "rejection_date")
  private LocalDateTime rejectionDate;

  /**
   * 심사 id
   */
  @Column(name = "audit_id")
  private String auditId;

  /**
   * 디자인 요청 여부
   */
  @Column(name = "design_request")
  private Boolean designRequest;

  /**
   * 채널 종류(오디존, 지하철)
   */
  @Column(name = "channel_type")
  private String channelType;

  /**
   * 승인날짜
   */
  @Column(name = "approval_date")
  private LocalDateTime approvalDate;

  /**
   * 메모
   */
  @Column(name = "memo")
  private String memo;

  /**
   * 기타 업종명
   */
  @Column(name = "etc_business_name")
  private String etcBusinessName;

  /**
   * 노출여부
   */
  @Column(name = "expo")
  private Boolean expo;

  /**
   * 발송 코드(승인, 보류, 종료 예정 발송 코드)
   */
  @Column(name = "send_code")
  private String sendCode;

}
