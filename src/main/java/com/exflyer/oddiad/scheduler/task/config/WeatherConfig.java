package com.exflyer.oddiad.scheduler.task.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@ToString
public class WeatherConfig {

  @Value("${weather.weather-dfs}")
  private String dfsApi;
}
