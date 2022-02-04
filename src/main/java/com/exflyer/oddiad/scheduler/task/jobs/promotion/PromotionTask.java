package com.exflyer.oddiad.scheduler.task.jobs.promotion;

import com.exflyer.oddiad.scheduler.task.service.promotion.PromotionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Slf4j
public class PromotionTask {

    @Autowired
    private PromotionService promotionService;

    /**
     * 프로모션 쿠폰
     * 오늘날짜 기준 만료일 체크 후 만료일 지났을 경우 promotion_coupon del '1'로 update
     */
    @Scheduled(cron = "${schedule.promotion.expiredDate}")
    public void promotion() {
        StopWatch stopWatch = new StopWatch("##### 프로모션 쿠폰 만료일 체크");
        stopWatch.start();
        promotionService.promotionCoupon();
        stopWatch.stop();
        log.info("{}", stopWatch.shortSummary());
    }
}
