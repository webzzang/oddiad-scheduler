package com.exflyer.oddiad.scheduler.task.dto;


import java.time.LocalDateTime;
import lombok.Data;

@Data
public class NotificationCollectDto {

  private long seq;
  private long targetGroupSeq;
  private String contents;
  private boolean advMessage;
  private String sendTime;
  private String senderPhoneNumber;
  private boolean auto;
  private long totalCount;
  private long successCount;
  private long failCount;
  private LocalDateTime regDate;
  private String regId;
  private boolean done;
  private String templateId;
  private boolean alrimTalk;
  private boolean targetCreateDone;
  private String targetCode;

}
