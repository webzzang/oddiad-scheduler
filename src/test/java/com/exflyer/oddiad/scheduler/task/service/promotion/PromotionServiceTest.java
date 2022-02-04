package com.exflyer.oddiad.scheduler.task.service.promotion;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.StopWatch;

@SpringBootTest
@TestPropertySource(properties = "app.scheduling.enable=false")
public class PromotionServiceTest {

    @Autowired
    private PromotionService promotionService;

    @DisplayName("프로모션 쿠폰 만료일 체크 ")
    @Test
    public void promotion() {
        promotionService.promotionCoupon();
    }
}
