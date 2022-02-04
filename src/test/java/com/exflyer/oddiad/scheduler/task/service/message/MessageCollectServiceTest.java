package com.exflyer.oddiad.scheduler.task.service.message;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest
@TestPropertySource(properties = "app.scheduling.enable=false")
class MessageCollectServiceTest {

  @Autowired
  private MessageCollectService messageCollectService;
  
  
  @DisplayName("대량 발송 대상 조회")
  @Test
  public void collect() throws Exception {
    messageCollectService.collectTarget();
    // given 
    
    // when
    
    // then
  }

}
