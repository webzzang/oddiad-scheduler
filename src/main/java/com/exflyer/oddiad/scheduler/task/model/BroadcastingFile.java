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
import lombok.NoArgsConstructor;

/**
 * 방송 파일
 */
@Data
@Entity
@Table(name = "broadcasting_file")
@NoArgsConstructor
public class BroadcastingFile implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 순번
   */
  @Id
  @Column(name = "seq", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seq;

  /**
   * 방송 순번
   */
  @Column(name = "broadcasting_seq", nullable = false)
  private Long broadcastingSeq;

  /**
   * 경로
   */
  @Column(name = "path", nullable = false)
  private String path;

  /**
   * 생성 날짜
   */
  @Column(name = "reg_date")
  private LocalDateTime regDate;

  public BroadcastingFile(Broadcasting broadcasting, Files files, LocalDateTime regDate) {
    this.broadcastingSeq = broadcasting.getSeq();
    if (broadcasting.getContentsType().equalsIgnoreCase("video")) {
      this.path = files.getConvertedPath();
    } else {
      this.path = files.getPath();
    }
    this.regDate = regDate;
  }
}
