package com.exflyer.oddiad.scheduler.task.service.promotion;

import com.exflyer.oddiad.scheduler.share.LocalDateUtils;
import com.exflyer.oddiad.scheduler.task.repository.PromotionCouponRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PromotionService {

    @Autowired
    private PromotionCouponRepository promotionCouponRepository;

    public void promotionCoupon() {
        promotionCouponRepository.updateExpiredDate(LocalDateUtils.korNowDateTimeYYYYMMDD());
    }

}
