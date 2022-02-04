package com.exflyer.oddiad.scheduler.task.dto;

import lombok.Data;

@Data
public class MediaConvertTarget {

  private Long advSeq;

  private Long fileSeq;

  private String s3FileKey;

  private String path;

}
