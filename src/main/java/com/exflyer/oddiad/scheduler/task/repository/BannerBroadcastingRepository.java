package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.model.BannerBroadcasting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BannerBroadcastingRepository extends JpaRepository<BannerBroadcasting, Long>,
  JpaSpecificationExecutor<BannerBroadcasting> {

}
