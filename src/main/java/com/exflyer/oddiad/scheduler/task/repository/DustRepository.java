package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.model.Dust;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DustRepository extends JpaRepository<Dust, String>, JpaSpecificationExecutor<Dust> {

  Dust findBySiName(String siName);
}
