package com.exflyer.oddiad.scheduler.task.dto;

import lombok.Data;

@Data
public class MessageBody {

  private String reText;
  private String reSend;
  private String templateCode;
  private String from;
  private String text;
  private String to;
  private String messageId;
  private String reservedTime;

}
