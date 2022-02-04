package com.exflyer.oddiad.scheduler.task.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.Data;

/**
 * 광고 파일
 */
@Data
@Embeddable
public class AdvFilePk implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long fileSeq;

  private Long advSeq;

}
