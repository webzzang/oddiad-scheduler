package com.exflyer.oddiad.scheduler.task.service.adv;

import com.exflyer.oddiad.scheduler.task.codes.AdvProgressCode;
import com.exflyer.oddiad.scheduler.task.model.Adv;
import com.exflyer.oddiad.scheduler.task.repository.AdvRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "app.scheduling.enable=false")
class AdvProgressServiceTest {

  @Autowired
  private AdvProgressService advProgressService;

  @Autowired
  private AdvRepository advRepository;
  
  @DisplayName("자동 신청 취소")
  @Test
  public void updateProgressCodeCancel() throws Exception {
    Optional<Adv> advOp = advRepository.findById(1L);
    Adv adv = advOp.get();
    adv.setProgressCode(AdvProgressCode.RESERVATION.getCode());
    adv.setCouponNumber("djfakld28323dsfa454");
    advRepository.saveAndFlush(adv);
    advProgressService.updateProgressCodeCancel();
  }

}
