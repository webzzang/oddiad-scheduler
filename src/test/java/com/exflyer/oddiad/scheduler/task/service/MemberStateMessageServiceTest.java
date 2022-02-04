package com.exflyer.oddiad.scheduler.task.service;

import com.exflyer.oddiad.scheduler.task.service.message.MemberStateMessageService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "app.scheduling.enable=false")
class MemberStateMessageServiceTest {

  @Autowired
  private MemberStateMessageService memberStateMessageService;


  @DisplayName("휴면 예정 고객 SMS 발송")
  @Test
  public void sendToMemberWillSleep() throws Exception {
    memberStateMessageService.sendToMemberWillSleep();
  }

  @DisplayName("탈퇴 예정 고객 SMS 발송")
  @Test
  public void sendToMemberWillWithdrawal() throws Exception {
    memberStateMessageService.sendToMemberWillWithdrawal();
  }

}
