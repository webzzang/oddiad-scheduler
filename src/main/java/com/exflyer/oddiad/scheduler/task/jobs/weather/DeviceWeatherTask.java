package com.exflyer.oddiad.scheduler.task.jobs.weather;


import com.exflyer.oddiad.scheduler.task.codes.TaskScheduleTime;
import com.exflyer.oddiad.scheduler.task.service.weather.DeviceWeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Slf4j
public class DeviceWeatherTask {

  @Autowired
  private DeviceWeatherService deviceWeatherService;


  /**
   * ### 장비 날씨 정보 update
   *
   * - 목적 : 장비들의 현재 날씨 정보를  update 한다.
   *
   * - 주기 : 1시간 마다
   * - Task Class : DeviceWeatherTask.java
   */
  @Scheduled(fixedDelay = 5 * TaskScheduleTime.MIN)
  public void updateDustCondition() {
    StopWatch stopWatch = new StopWatch("##### 장비 날씨 수정");
    stopWatch.start();
    try {
      deviceWeatherService.update();
    } catch (Exception e) {
      log.error("device weather update error {}", e);
    }
    stopWatch.stop();
    log.info("{}", stopWatch.shortSummary());
  }


}
