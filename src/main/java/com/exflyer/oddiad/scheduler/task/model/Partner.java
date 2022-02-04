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
 * 파트너
 */
@Data
@Entity
@Table(name = "partner")
public class Partner implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 순번
   */
  @Id
  @Column(name = "seq", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seq;

  /**
   * 채널 종류(오디존, 지하철)
   */
  @Column(name = "channel_type")
  private String channelType;

  /**
   * 매장 이름
   */
  @Column(name = "mall_name")
  private String mallName;

  /**
   * 주소
   */
  @Column(name = "addr")
  private String addr;

  /**
   * 상세 주소
   */
  @Column(name = "detail_addr")
  private String detailAddr;

  /**
   * 소유자 이름
   */
  @Column(name = "owner_name")
  private String ownerName;

  /**
   * 소유자 전화 번호
   */
  @Column(name = "owner_phone_number")
  private String ownerPhoneNumber;

  /**
   * 생성 날짜
   */
  @Column(name = "reg_date", nullable = false)
  private Date regDate;

  /**
   * 생성 id
   */
  @Column(name = "reg_id", nullable = false)
  private String regId;

  /**
   * 변경 날짜
   */
  @Column(name = "mod_date")
  private Date modDate;

  /**
   * 이름
   */
  @Column(name = "mod_id")
  private String modId;

  /**
   * 설명
   */
  @Column(name = "description")
  private String description;

  /**
   * 운영 여부
   */
  @Column(name = "operation")
  private Integer operation;

  /**
   * 메모
   */
  @Column(name = "memo")
  private String memo;

  /**
   * 총 슬롯
   */
  @Column(name = "total_slot", nullable = false)
  private Integer totalSlot;

  /**
   * 슬롯당 노출 시간
   */
  @Column(name = "slot_video_time", nullable = false)
  private BigDecimal slotVideoTime;

  /**
   * 슬롯당 금액
   */
  @Column(name = "slot_price", nullable = false)
  private BigDecimal slotPrice;

  /**
   * 운영 요일
   */
  @Column(name = "operation_week")
  private String operationWeek;

  /**
   * 운영 시작 시간
   */
  @Column(name = "operation_start_time")
  private String operationStartTime;

  /**
   * 운영 종료 시간
   */
  @Column(name = "operation_end_time")
  private String operationEndTime;

  /**
   * 일 노출 카운트
   */
  @Column(name = "day_expo_count", nullable = false)
  private Integer dayExpoCount;

  /**
   * 지하철 노선
   */
  @Column(name = "subway_line")
  private String subwayLine;

  /**
   * 위치(지하철 광고 위치)
   */
  @Column(name = "location")
  private String location;

  /**
   * 화면(지하철 광고 화면)
   */
  @Column(name = "display")
  private String display;

  /**
   * 요약
   */
  @Column(name = "summary")
  private String summary;

  /**
   * 배지 코드
   */
  @Column(name = "badge_code")
  private String badgeCode;

  /**
   * 광고 사례 노출 여부
   */
  @Column(name = "adv_case_expo")
  private Integer advCaseExpo;

  /**
   * 측면 화면 서비스 코드
   */
  @Column(name = "side_display_service_code")
  private String sideDisplayServiceCode;

  /**
   * 아래 화면 서비스 코드
   */
  @Column(name = "bottom_display_service_code")
  private String bottomDisplayServiceCode;

  /**
   * 우편번호
   */
  @Column(name = "zipcode")
  private String zipcode;

  /**
   * 위도
   */
  @Column(name = "latitude")
  private Double latitude;

  /**
   * 경도
   */
  @Column(name = "longitude")
  private Double longitude;

  /**
   * 그리드 x
   */
  @Column(name = "grid_x")
  private Double gridX;

  /**
   * 그리드 y
   */
  @Column(name = "grid_y")
  private Double gridY;

  /**
   * 지번주소
   */
  @Column(name = "old_addr")
  private String oldAddr;

  @Column(name = "addr_si")
  private String addrSi;

  @Column(name = "addr_gu")
  private String addrGu;

  @Column(name = "addr_dong")
  private String addrDong;




}
