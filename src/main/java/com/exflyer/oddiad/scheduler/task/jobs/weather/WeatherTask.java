package com.exflyer.oddiad.scheduler.task.jobs.weather;

import com.exflyer.oddiad.scheduler.task.codes.TaskScheduleTime;
import com.exflyer.oddiad.scheduler.task.service.weather.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Slf4j
public class WeatherTask {

  @Autowired
  private WeatherService weatherService;


  /**
   * ### 날씨 정보 update
   *
   * - 목적 : 오디존 Grid X Y 좌표를 이용하여 날씨 정보를 update 한다.
   *
   * - 주기 : 1시간 마다
   * - 외부연동 : 공공 데이터
   * - Task Class : DustTask.java
   * @throws Exception
   */
  @Scheduled(fixedDelay = 6 * TaskScheduleTime.HOUR)
  public void updateWeather() throws Exception {
    StopWatch stopWatch = new StopWatch("##### 날씨 조회");
    stopWatch.start();
    try {
      weatherService.updateWeather();
    } catch (Exception e) {
      log.error("weather update error {}", e);
    }
    stopWatch.stop();
    log.info("{}", stopWatch.shortSummary());
  }

}
