package com.exflyer.oddiad.scheduler.task.service.adv;

import com.exflyer.oddiad.scheduler.share.LocalDateUtils;
import com.exflyer.oddiad.scheduler.task.codes.AdvProgressCode;
import com.exflyer.oddiad.scheduler.task.model.Adv;
import com.exflyer.oddiad.scheduler.task.model.PromotionCoupon;
import com.exflyer.oddiad.scheduler.task.repository.AdvRepository;
import com.exflyer.oddiad.scheduler.task.repository.PromotionCouponRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AdvProgressService {

  @Autowired
  private AdvRepository advRepository;

  @Autowired
  private PromotionCouponRepository promotionCouponRepository;

  @Transactional
  public void updateProgressCodeCancel() {
    LocalDateTime before = LocalDateUtils.korNowDateTime().minusDays(1);
    LocalDateTime now = LocalDateUtils.korNowDateTime().minusMinutes(2);
    List<Adv> cancelTargetList = advRepository.findByProgressCodeAndRegDateBetween(AdvProgressCode.RESERVATION.getCode(), before, now);
    for (Adv adv : cancelTargetList) {
      adv.setProgressCode(AdvProgressCode.RESERVATION_CANCEL.getCode());
      if (StringUtils.isNotBlank(adv.getCouponNumber())) {
        PromotionCoupon promotionCoupon =  promotionCouponRepository.findByCouponCode(adv.getCouponNumber());
        promotionCoupon.setUsable(false);
        promotionCoupon.setUsingDate(null);
        promotionCouponRepository.save(promotionCoupon);
      }
      adv.setCouponNumber(null);
      adv.setModDate(LocalDateUtils.korNowDateTime());
    }
    advRepository.saveAll(cancelTargetList);
  }
}
