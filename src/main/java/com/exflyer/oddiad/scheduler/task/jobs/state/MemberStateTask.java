package com.exflyer.oddiad.scheduler.task.jobs.state;

import com.exflyer.oddiad.scheduler.task.service.state.MemberStateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Slf4j
public class MemberStateTask {

  @Autowired
  private MemberStateService memberStateService;

  @Scheduled(cron = "${schedule.account.sleep-member}")
  public void updateSleepMember()  {
    StopWatch updateSleepMemberWatch = new StopWatch("##### 회원 휴면 처리");
    updateSleepMemberWatch.start();
    try {
      memberStateService.updateSleepStatus();
    } catch (Exception e) {
      log.error("send sleep member message error {}", e);
    }
    updateSleepMemberWatch.stop();
    log.info("{}", updateSleepMemberWatch.shortSummary());

  }

  @Scheduled(cron = "${schedule.account.withdrawal-member}")
  public void updateWithdrawal()  {
    StopWatch updateWithdrawalStopWatch = new StopWatch("##### 회원 탈퇴 처리");
    updateWithdrawalStopWatch.start();
    try {
      memberStateService.updateWithdrawal();
    } catch (Exception e) {
      log.error("send sleep member message error {}", e);
    }
    updateWithdrawalStopWatch.stop();
    log.info("{}", updateWithdrawalStopWatch.shortSummary());

  }

}
