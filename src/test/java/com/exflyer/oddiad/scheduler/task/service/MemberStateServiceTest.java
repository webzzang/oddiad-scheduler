package com.exflyer.oddiad.scheduler.task.service;

import com.exflyer.oddiad.scheduler.task.service.state.MemberStateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "app.scheduling.enable=false")
class MemberStateServiceTest {

  @Autowired
  private MemberStateService memberStateService;

  @DisplayName("회원 휴면 처리")
  @Test
  public void updateSleepState() throws Exception {
    memberStateService.updateSleepStatus();
  }

  @DisplayName("회원 탈퇴 처리")
  @Test
  public void updateWithdrawal() throws Exception {
    memberStateService.updateWithdrawal();
  }

}
