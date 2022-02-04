package com.exflyer.oddiad.scheduler.task.service.weather;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest
@TestPropertySource(properties = "app.scheduling.enable=false")
class DustServiceTest {

  @Autowired
  private DustService dustService;


  @DisplayName("미세먼지")
  @Test
  public void updateDust() throws Exception {
    dustService.updateDust();
  }

}
