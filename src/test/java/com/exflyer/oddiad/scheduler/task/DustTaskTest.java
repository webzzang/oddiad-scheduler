package com.exflyer.oddiad.scheduler.task;

import com.exflyer.oddiad.scheduler.task.jobs.weather.DustTask;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "app.scheduling.enable=false")
class DustTaskTest {

  @Autowired
  private DustTask dustTask;
  
  @DisplayName("미세먼지 정보 업데이트")
  @Test
  public void updateDust() throws Exception {
    dustTask.updateDustCondition();
  }

}
