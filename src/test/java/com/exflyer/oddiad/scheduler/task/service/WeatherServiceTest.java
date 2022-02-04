package com.exflyer.oddiad.scheduler.task.service;

import com.exflyer.oddiad.scheduler.task.service.weather.WeatherService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "app.scheduling.enable=false")
class WeatherServiceTest {

  @Autowired
  private WeatherService weatherService;

  @DisplayName("날씨 정보 조회")
  @Test
  public void requestWeather() throws Exception {
    weatherService.updateWeather();
  }

}
