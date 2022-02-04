package com.exflyer.oddiad.scheduler.task.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 회원(광고주) 정보
 */
@Data
@Entity
@Table(name = "member")
@NoArgsConstructor
public class Member implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * id
   */
  @Id
  @Column(name = "id", nullable = false)
  private String id;

  /**
   * 이름
   */
  @Column(name = "name", nullable = false)
  private String name;

  /**
   * 비밀번호
   */
  @Column(name = "password", nullable = false)
  private String password;

  /**
   * 전화번호
   */
  @Column(name = "phone_number")
  private String phoneNumber;

  /**
   * 수신 동의
   */
  @Column(name = "receive_consent", nullable = false)
  private boolean receiveConsent;

  /**
   * 인증 코드
   */
  @Column(name = "auth_code")
  private String authCode;

  /**
   * 가입 날짜
   */
  @Column(name = "signup_date", nullable = false)
  private LocalDateTime signupDate;

  /**
   * 변경 관리자 ID
   */
  @Column(name = "mod_id")
  private String modId;

  /**
   * 변경 날짜
   */
  @Column(name = "mod_date")
  private LocalDateTime modDate;

  /**
   * 상태 코드
   */
  @Column(name = "state_code")
  private String stateCode;

  /**
   * 비밀번호 오류 카운트
   */
  @Column(name = "password_error_count")
  private Integer passwordErrorCount;

  /**
   * 비밀번호 변경 날짜
   */
  @Column(name = "password_mod_date")
  private LocalDateTime passwordModDate;

  /**
   * 로그인 날짜
   */
  @Column(name = "login_date")
  private LocalDateTime loginDate;

  /**
   * 메모
   */
  @Column(name = "memo")
  private String memo;

  /**
   * 개인구분(1:개인, 0:사업자)
   */
  @Column(name = "member_gbn")
  private Integer memberGbn;

  private String email;

  public Member(String name, String phoneNumber) {
    this.name = name;
    this.phoneNumber = phoneNumber;
  }
}
