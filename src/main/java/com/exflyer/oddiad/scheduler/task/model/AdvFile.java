package com.exflyer.oddiad.scheduler.task.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

/**
 * 광고 파일
 */
@Data
@Entity
@Table(name = "adv_file")
public class AdvFile implements Serializable {

  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private AdvFilePk advFilePk;

  /**
   * 종류(doc, image, video)
   */
  @Column(name = "type", nullable = false)
  private String type;

  /**
   * 생성 날짜
   */
  @Column(name = "reg_date", nullable = false)
  private Date regDate;

  /**
   * 노출순서
   */
  @Column(name = "view_order")
  private Integer viewOrder;

  /**
   * 미디어 컨버팅된 경로
   */
  @Column(name = "converted_path")
  private String convertedPath;

}
