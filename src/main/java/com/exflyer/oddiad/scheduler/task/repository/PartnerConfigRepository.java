package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.model.PartnerConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PartnerConfigRepository extends JpaRepository<PartnerConfig, Void>,
  JpaSpecificationExecutor<PartnerConfig> {

  PartnerConfig findByType(String ptt001);
}
