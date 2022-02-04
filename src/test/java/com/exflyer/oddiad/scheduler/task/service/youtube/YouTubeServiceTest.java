package com.exflyer.oddiad.scheduler.task.service.youtube;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "app.scheduling.enable=false")
class YouTubeServiceTest {

  @Autowired
  private YouTubeService youTubeService;

  @DisplayName("유투브 영상 저장")
  @Test
  public void savePlayItem() throws Exception {
    youTubeService.savePlayItem();
  }

}
