package com.exflyer.oddiad.scheduler.task.jobs.message;

import com.exflyer.oddiad.scheduler.task.codes.TaskScheduleTime;
import com.exflyer.oddiad.scheduler.task.service.message.AdvMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Slf4j
public class AdvMessageTask {

  @Autowired
  private AdvMessageService advMessageService;


  /**
   * ### 광고 상태 변경 메세지 전송
   *
   * - 목적 : 광고 상태가 변경 될 경우 광고주(사용자) 에게 알림 메세지를 전달 한다.
   *
   * - 조건
   *   - 승인
   *   - 반려
   *   - 종료 예정
   * - 주기
   *   - 승인 반료: 10분마다.
   *   - 종료 예정  : 매일 11시
   * - Task Class : AdvMessageTask.java
   */
  @Scheduled(fixedDelay = 10 * TaskScheduleTime.MIN)
  public void sendToMemberAcceptAdv()  {
    StopWatch advAcceptStopWatch = new StopWatch("##### 광고 승인 메세지 전송");
    advAcceptStopWatch.start();
    try {
      advMessageService.sendToMemberAcceptState();
    } catch (Exception e) {
      log.error("send to member will withdrawal error {}", e);
    }
    advAcceptStopWatch.stop();
    log.info("{}", advAcceptStopWatch.shortSummary());

    StopWatch denyAdvStopWatch = new StopWatch("##### 광고 보류 메세지 전송");
    denyAdvStopWatch.start();
    try {
      advMessageService.sendToMemberDenyState();
    } catch (Exception e) {
      log.error("send to member will sleep error {}", e);
    }
    denyAdvStopWatch.stop();
    log.info("{}", denyAdvStopWatch.shortSummary());
  }

  /**
   * 광고 종료 예정
   */
  @Scheduled(cron = "${schedule.message.adv-end}")
  public void sendToMemberWillDoneAdv()  {
    StopWatch willDoneAdvStopWatch = new StopWatch("##### 광고 종료 예정 메세지 전송");
    willDoneAdvStopWatch.start();
    try {
      advMessageService.sendToMemberWillDone();
    } catch (Exception e) {
      log.error("send to member will sleep error {}", e);
    }
    willDoneAdvStopWatch.stop();
    log.info("{}", willDoneAdvStopWatch.shortSummary());
  }



}
