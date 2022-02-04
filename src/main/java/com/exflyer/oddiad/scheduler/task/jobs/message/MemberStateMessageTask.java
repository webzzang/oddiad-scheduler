package com.exflyer.oddiad.scheduler.task.jobs.message;

import com.exflyer.oddiad.scheduler.task.service.message.MemberStateMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Slf4j
public class MemberStateMessageTask {

  @Autowired
  private MemberStateMessageService memberStateMessageService;

  @Scheduled(cron = "${schedule.message.sleep-member}")
  public void sendToMemberWillSleep()  {
    StopWatch sendToMemberWillSleepStopWatch = new StopWatch("##### 휴면 예정 회원 메세지 전송");
    sendToMemberWillSleepStopWatch.start();
    try {
      memberStateMessageService.sendToMemberWillSleep();
    } catch (Exception e) {
      log.error("send to member will sleep error {}", e);
    }
    sendToMemberWillSleepStopWatch.stop();
    log.info("{}", sendToMemberWillSleepStopWatch.shortSummary());
  }

  @Scheduled(cron = "${schedule.message.withdrawal-member}")
  public void sendToMemberWillWithdrawal()  {
    StopWatch sendToMemberWillWithdrawalStopWatch = new StopWatch("##### 자동 탈퇴 예정 회원 메세지 전송");
    sendToMemberWillWithdrawalStopWatch.start();
    try {
      memberStateMessageService.sendToMemberWillWithdrawal();
    } catch (Exception e) {
      log.error("send to member will withdrawal error {}", e);
    }
    sendToMemberWillWithdrawalStopWatch.stop();
    log.info("{}", sendToMemberWillWithdrawalStopWatch.shortSummary());
  }
}
