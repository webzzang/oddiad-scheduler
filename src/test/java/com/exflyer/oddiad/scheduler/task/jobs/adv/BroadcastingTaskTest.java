package com.exflyer.oddiad.scheduler.task.jobs.adv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "app.scheduling.enable=false")
class BroadcastingTaskTest {

  @Autowired
  private BroadcastingTask broadcastingTask;

  @DisplayName("신규 장비 매핑 테스트")
  @Test
  public void addNewMappingDevice() throws Exception {
    broadcastingTask.addNewPartnerMappingDevice();
  }


  @DisplayName("광고 재편성")
  @Test
  public void advEditing() throws Exception {
    broadcastingTask.adEditing();
  }

}
