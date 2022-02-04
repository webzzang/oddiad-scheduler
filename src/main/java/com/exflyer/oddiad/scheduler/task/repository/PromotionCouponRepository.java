package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.model.Member;
import com.exflyer.oddiad.scheduler.task.model.PromotionCoupon;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PromotionCouponRepository extends JpaRepository<PromotionCoupon, Long>,
  JpaSpecificationExecutor<PromotionCoupon> {

  PromotionCoupon findByCouponCode(String couponCode);

  @Modifying
  @Transactional
  @Query(value = "update promotion_coupon set del = true where expired_date < :expiredDate "
      + "and usable = false and del = false", nativeQuery = true)
  void updateExpiredDate(String expiredDate);
}
