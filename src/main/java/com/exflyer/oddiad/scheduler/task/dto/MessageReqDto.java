package com.exflyer.oddiad.scheduler.task.dto;

import java.util.List;
import lombok.Data;

@Data
public class MessageReqDto {


  private List<MessageBody> messages;
  private String yellowidKey;
  private String deptcode;
  private String usercode;
}
