package com.exflyer.oddiad.scheduler.task.model;

import com.exflyer.oddiad.scheduler.task.dto.BroadcastingDevice;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 방송
 */
@Data
@Entity
@Table(name = "broadcasting")
@NoArgsConstructor
public class Broadcasting implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 순번
   */
  @Id
  @Column(name = "seq", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seq;

  /**
   * 플레이어 시리얼
   */
  @Column(name = "device_id", nullable = false)
  private String deviceId;

  /**
   * 광고 순번
   */
  @Column(name = "adv_seq", nullable = false)
  private Long advSeq;

  /**
   * 파트너 순번
   */
  @Column(name = "partner_seq", nullable = false)
  private Long partnerSeq;

  /**
   * 수정 날짜
   */
  @Column(name = "reg_date", nullable = false)
  private LocalDateTime regDate;

  /**
   * 변경 id
   */
  @Column(name = "mod_id")
  private String modId;

  /**
   * 변경 날짜
   */
  @Column(name = "mod_date")
  private LocalDateTime modDate;

  /**
   * 노출 여부
   */
  @Column(name = "expo")
  private Boolean expo;

  /**
   * 정렬 번호
   */
  @Column(name = "ordering")
  private Integer ordering;

  /**
   * 영상 시간
   */
  @Column(name = "video_time", nullable = false)
  private Integer videoTime;

  /**
   * 내용 종류 코드(video, image 등)
   */
  @Column(name = "contents_type")
  private String contentsType;

  public Broadcasting(BroadcastingDevice broadcastingDevice, LocalDateTime modDate, boolean image) {
    this.deviceId = broadcastingDevice.getDeviceId();
    this.advSeq = 0L;
    this.partnerSeq = broadcastingDevice.getPartnerSeq();
    this.regDate = modDate;
    this.expo = true;
    this.ordering = 0;
    this.videoTime = broadcastingDevice.getSlotVideoTime();
    if (image) {
      this.contentsType = "image";
    } else {
      this.contentsType = "video";
    }

    this.modDate = modDate;
    this.modId = "SCHEDULER";
  }

  public void setInfoByBroadcastingDevice(BroadcastingDevice broadcastingDevice, Integer orderingCount,
    LocalDateTime modDate) {
    this.deviceId = broadcastingDevice.getDeviceId();
    this.advSeq = broadcastingDevice.getAdvSeq();
    this.partnerSeq = broadcastingDevice.getPartnerSeq();
    this.regDate = modDate;
    if (this.expo == null) {
      this.expo = true;
    }
    if (this.ordering == null || this.ordering < 0) {
      this.ordering = orderingCount;
    }
    this.videoTime = broadcastingDevice.getSlotVideoTime();
    this.contentsType = broadcastingDevice.getContentsType();
    this.modDate = modDate;
    this.modId = "SCHEDULER";
  }
}
