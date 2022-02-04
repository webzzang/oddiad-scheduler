package com.exflyer.oddiad.scheduler.task.model;

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
 * 프로모션 쿠폰
 */
@Data
@Entity
@Table(name = "promotion_coupon")
public class PromotionCoupon implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 순번
   */
  @Id
  @Column(name = "seq", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seq;

  /**
   * 홍보 순번
   */
  @Column(name = "promotion_seq", nullable = false)
  private Long promotionSeq;

  /**
   * 쿠폰 번호
   */
  @Column(name = "coupon_code", nullable = false)
  private String couponCode;

  /**
   * 사용 여부
   */
  @Column(name = "usable", nullable = false)
  private Boolean usable;

  /**
   * 회원 id
   */
  @Column(name = "member_id")
  private String memberId;

  /**
   * 사용 날짜
   */
  @Column(name = "using_date")
  private LocalDateTime usingDate;

  /**
   * 생성 날짜
   */
  @Column(name = "reg_date", nullable = false)
  private LocalDateTime regDate;

  /**
   * 만료 날짜
   */
  @Column(name = "expired_date", nullable = false)
  private String expiredDate;

}
