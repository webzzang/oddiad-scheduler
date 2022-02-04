package com.exflyer.oddiad.scheduler.task.service;

import com.exflyer.oddiad.scheduler.task.service.message.VocMessageService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "app.scheduling.enable=false")
class VocMessageServiceTest {

  @Autowired
  private VocMessageService vocMessageService;

  @DisplayName("1:1 문의 답변 완료 메세지 발송")
  @Test
  public void sendVocCommentMessage() throws Exception {
    vocMessageService.sendToMemberVocComment();
  }

}
