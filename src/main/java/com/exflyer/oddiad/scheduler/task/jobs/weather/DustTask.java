package com.exflyer.oddiad.scheduler.task.jobs.weather;

import com.exflyer.oddiad.scheduler.task.codes.TaskScheduleTime;
import com.exflyer.oddiad.scheduler.task.service.weather.DustService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Slf4j
public class DustTask {

  @Autowired
  private DustService dustService;


  /**
   * ### 미세먼지 정보 update
   *
   * - 목적 : 오디존 주소를 기반으로 미세먼지 정보를 update 한다.
   *
   * - 주기 : 1시간 마다
   * - Task Class : DustTask.java
   * @throws Exception
   */
  @Scheduled(fixedDelay = 1 * TaskScheduleTime.HOUR)
  public void updateDustCondition() throws Exception {
    StopWatch stopWatch = new StopWatch("##### 미세먼지 조회");
    stopWatch.start();

    try {
      dustService.updateDust();
    } catch (Exception e) {
      log.error("dust condition update error {}", e);

    }
    stopWatch.stop();
    log.info("{}", stopWatch.shortSummary());
  }

}
