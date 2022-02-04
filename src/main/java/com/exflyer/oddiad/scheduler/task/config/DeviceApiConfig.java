package com.exflyer.oddiad.scheduler.task.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class DeviceApiConfig {

  @Value("${device-api}")
  private String deviceApiHost;
}
