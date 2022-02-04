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
 * 파일
 */
@Data
@Entity
@Table(name = "files")
public class Files implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 순번
   */
  @Id
  @Column(name = "seq", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seq;

  /**
   * s3_file_key
   */
  @Column(name = "s3_file_key", nullable = false)
  private String s3FileKey;

  /**
   * s3_bucket
   */
  @Column(name = "s3_bucket", nullable = false)
  private String s3Bucket;

  /**
   * 경로
   */
  @Column(name = "path", nullable = false)
  private String path;

  /**
   * 수정 날짜
   */
  @Column(name = "reg_date", nullable = false)
  private Date regDate;

  /**
   * 이름
   */
  @Column(name = "name")
  private String name;

  /**
   * 확장자
   */
  @Column(name = "extension", nullable = false)
  private String extension;

  /**
   * 종류(S3, Local 등)
   */
  @Column(name = "type")
  private String type;

  /**
   * 등록 id
   */
  @Column(name = "reg_id")
  private String regId;

  /**
   * 매핑 완료 여부
   */
  @Column(name = "mapping_done")
  private Boolean mappingDone;

  /**
   * 파일 컨텐츠 타입
   */
  @Column(name = "content_type")
  private String contentType;

  /**
   * 미디어 컨버터 변환 여부
   */
  @Column(name = "converted", nullable = false)
  private Boolean converted;

  /**
   * 미디어 컨버팅된 경로
   */
  @Column(name = "converted_path")
  private String convertedPath;

}
