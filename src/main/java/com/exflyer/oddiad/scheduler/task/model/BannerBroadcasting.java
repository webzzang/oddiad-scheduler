package com.exflyer.oddiad.scheduler.task.model;

import com.exflyer.oddiad.scheduler.task.dto.ExpoBanner;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 배너 방송
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "banner_broadcasting")
public class BannerBroadcasting implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 배너 순번
   */
  @Id
  @Column(name = "banner_seq", nullable = false)
  private Long bannerSeq;

  /**
   * 영상 시간
   */
  @Column(name = "video_time")
  private Integer videoTime;

  /**
   * 경로
   */
  @Column(name = "path")
  private String path;

  /**
   * 위치(side, bottom)
   */
  @Column(name = "location_code")
  private String locationCode;

  public BannerBroadcasting(ExpoBanner expoBanner) {
    this.bannerSeq = expoBanner.getBannerSeq();
    this.videoTime = 15;
    this.path = expoBanner.getPath();
    this.locationCode = expoBanner.getLocationCode();
  }
}
