package com.exflyer.oddiad.scheduler.task.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class OddiUrlConfig {

  @Value("${oddi.user-web-host}")
  private String userWebHost;

  @Value("${oddi.user-web-voc-detail}")
  private String vocDetail;

  @Value("${oddi.manager-web-host}")
  private String managerWebHost;


}
