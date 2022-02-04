package com.exflyer.oddiad.scheduler.task;

import com.exflyer.oddiad.scheduler.task.jobs.geography.GeographyTask;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "app.scheduling.enable=false")
class GeographyTaskTest {

  @Autowired
  private GeographyTask geographyTask;

  @DisplayName("지번 주소로 위경도 gridXY 조회")
  @Test
  public void convert() throws Exception {
    geographyTask.updateGeographyInfo();
  }

}
