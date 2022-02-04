package com.exflyer.oddiad.scheduler.task.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 라이브 스트리밍
 */
@Data
@Entity
@Table(name = "live_streaming")
public class LiveStreaming implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 라이브채널 대표명
   */
  @Column(name = "live_channel_title", nullable = false)
  private String liveChannelTitle;

  /**
   * 라이브스트림 채널ID
   */
  @Id
  @Column(name = "live_stream_channel_id", nullable = false)
  private String liveStreamChannelId;

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
   * 변경 id
   */
  @Column(name = "mod_id")
  private String modId;

}
