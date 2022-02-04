package com.exflyer.oddiad.scheduler.task.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@ToString
public class DustConfig {

  @Value("${weather.open-api-host}")
  private String host;

  @Value("${weather.air-api-path}")
  private String apiPath;

  @Value("${weather.serviceKey}")
  private String serviceKey;

}
