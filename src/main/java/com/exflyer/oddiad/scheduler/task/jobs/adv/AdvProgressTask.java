package com.exflyer.oddiad.scheduler.task.jobs.adv;

import com.exflyer.oddiad.scheduler.task.codes.TaskScheduleTime;
import com.exflyer.oddiad.scheduler.task.service.adv.AdvProgressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Slf4j
public class AdvProgressTask {

  @Autowired
  private AdvProgressService advProgressService;


  /**
   * ### 광고 신청 취소 처리
   *
   * - 목적 : 기준 시간 동안 결제가 이루어 지지 않는 광고를 취소 상태로 변경 한다.
   * - 조건 :
   *   - 신청 상태
   *   - 전일 부터 현재 시간 2분 전에 생성된 광고
   * - 테이블
   *   - adv
   * - 주기 :  처리후 10분
   * - Task Class : AdvProgressTask.java
   */
  @Scheduled(fixedDelay = 10 * TaskScheduleTime.MIN)
  public void updateProgressCodeCancel(){
    StopWatch stopWatch = new StopWatch("##### 신청 취소 처리");
    stopWatch.start();
    advProgressService.updateProgressCodeCancel();
    stopWatch.stop();
    log.info("{}", stopWatch.shortSummary());
  }

}
