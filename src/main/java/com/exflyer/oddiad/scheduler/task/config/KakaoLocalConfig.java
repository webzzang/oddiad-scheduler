package com.exflyer.oddiad.scheduler.task.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@ToString
public class KakaoLocalConfig {

  @Value("${kakao.local-host}")
  private String host;

  @Value("${kakao.rest-key}")
  private String restApiKey;

}
