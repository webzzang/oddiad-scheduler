package com.exflyer.oddiad.scheduler.task.service.adv;

import com.exflyer.oddiad.scheduler.share.LocalDateUtils;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "app.scheduling.enable=false")
class BroadcastingServiceTest {

  @Autowired
  private BroadcastingService broadcastingService;

  @DisplayName("광고 편성")
  @Test
  public void addAd() throws Exception {
    LocalDateTime nowDate = LocalDateUtils.korNowDateTime();
    broadcastingService.addAdv(nowDate);
  }

  @DisplayName("배너 편성")
  @Test
  public void addBannerBroadCasting() throws Exception {
    broadcastingService.addBannerBroadCasting();
  }

}
