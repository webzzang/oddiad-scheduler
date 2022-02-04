package com.exflyer.oddiad.scheduler.task.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * voc
 */
@Data
@Entity
@Table(name = "voc")
public class Voc implements Serializable {

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
   * 이름
   */
  @Column(name = "name")
  private String name;

  /**
   * 전화번호
   */
  @Column(name = "phone_number")
  private String phoneNumber;

  /**
   * 제목
   */
  @Column(name = "title")
  private String title;

  /**
   * 내용
   */
  @Column(name = "contents")
  private String contents;

  /**
   * 등록날짜
   */
  @Column(name = "reg_date", nullable = false)
  private Date regDate;

  /**
   * 답변
   */
  @Column(name = "answer")
  private String answer;

  /**
   * 답변 생성 날짜
   */
  @Column(name = "answer_reg_date")
  private Date answerRegDate;

  /**
   * 답변 생성 id
   */
  @Column(name = "answer_reg_id")
  private String answerRegId;

  /**
   * 문의유형코드
   */
  @Column(name = "inquiry_type_code")
  private String inquiryTypeCode;

  /**
   * 발송 여부
   */
  @Column(name = "send_done", nullable = false)
  private Boolean sendDone;

  @Column(name = "uniq_code")
  private String uniqCode;

}
