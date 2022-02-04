package com.exflyer.oddiad.scheduler.task.service;

import com.exflyer.oddiad.scheduler.task.service.weather.DeviceWeatherService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "app.scheduling.enable=false")
class DeviceWeatherServiceTest {

  @Autowired
  private DeviceWeatherService deviceWeatherService;

  @DisplayName("장비 날씨 update ")
  @Test
  public void updateDeviceWeather() throws Exception {
    deviceWeatherService.update();
  }

}
