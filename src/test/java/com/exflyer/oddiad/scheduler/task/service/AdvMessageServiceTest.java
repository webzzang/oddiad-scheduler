package com.exflyer.oddiad.scheduler.task.service;

import com.exflyer.oddiad.scheduler.task.service.message.AdvMessageService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "app.scheduling.enable=false")
class AdvMessageServiceTest {

  @Autowired
  private AdvMessageService advMessageService;
  
  
  @DisplayName("광고 승인 메세지 발송")
  @Test
  public void sendToMemberAcceptAdv() throws Exception {
    advMessageService.sendToMemberAcceptState();
  }

  @DisplayName("광고 보류 메세지 발송")
  @Test
  public void sendToMemberDenyState() throws Exception {
    advMessageService.sendToMemberDenyState();
  }

  @DisplayName("광고 만료 예정정 세지 발송")
  @Test
  public void sendToMemberWillDone() throws Exception {
    advMessageService.sendToMemberWillDone();
  }

}
