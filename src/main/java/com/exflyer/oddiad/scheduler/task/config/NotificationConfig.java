package com.exflyer.oddiad.scheduler.task.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@ToString
public class NotificationConfig {

  @Value("${notification.sender-phone}")
  private String senderPhone;

}
