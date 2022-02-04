package com.exflyer.oddiad.scheduler.task.jobs.geography;

import com.exflyer.oddiad.scheduler.task.codes.TaskScheduleTime;
import com.exflyer.oddiad.scheduler.task.service.geography.GeographyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Slf4j
public class GeographyTask {

  @Autowired
  private GeographyService geographyService;

  /**
   * ### 지역 정보 관련
   *
   * - 목적 : 날씨 정보를 위해 파트너의 그리드 좌표를 구한다.
   *
   * - 조건
   *   - 오디존 파트너
   * - 테이블
   *   - partner
   * - 주기 :  6시간 마다
   * - Task Class : GeographyTask.java
   */
  @Scheduled(fixedDelay = 6 * TaskScheduleTime.HOUR)
  public void updateGeographyInfo()  {
    StopWatch stopWatch = new StopWatch("##### 격자 그리조 조회");
    stopWatch.start();
    try {
      geographyService.updateGeographyInfo();
    } catch (Exception e) {
      log.error("grid x y update error {}", e);
    }
    stopWatch.stop();
    log.info("{}", stopWatch.shortSummary());
  }

}
