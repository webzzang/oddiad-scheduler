package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.model.KakaoTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface KakaoTemplateRepository extends JpaRepository<KakaoTemplate, String>,
  JpaSpecificationExecutor<KakaoTemplate> {

}
