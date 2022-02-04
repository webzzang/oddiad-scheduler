package com.exflyer.oddiad.scheduler.task.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 유튜브 vod목록
 */
@Data
@Entity
@Table(name = "youtube")
public class Youtube implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 유튜브 ID
   */
  @Id
  @Column(name = "youtube_id", nullable = false)
  private String youtubeId;

  /**
   * 유튜브 플레이 ID
   */
  @Column(name = "youtube_play_id", nullable = false)
  private String youtubePlayId;

  /**
   * 유튜브 제목
   */
  @Column(name = "youtube_title", nullable = false)
  private String youtubeTitle;

  /**
   * 유뷰브 설명
   */
  @Column(name = "youtube_description")
  private String youtubeDescription;

  /**
   * 유튜브 썸네일
   */
  @Column(name = "youtube_thumbnails", nullable = false)
  private String youtubeThumbnails;

  /**
   * 유튜브 경로
   */
  @Column(name = "youtube_url", nullable = false)
  private String youtubeUrl;

  /**
   * 유튜브 등록일
   */
  @Column(name = "youtube_reg_date", nullable = false)
  private String youtubeRegDate;

  /**
   * 생성 날짜
   */
  @Column(name = "reg_date", nullable = false)
  private LocalDateTime regDate;

  @Column(name = "expo")
  private boolean expo;


}
