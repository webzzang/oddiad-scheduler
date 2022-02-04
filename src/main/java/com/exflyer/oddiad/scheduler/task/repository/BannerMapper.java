package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.dto.ExpoBanner;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerMapper {

  List<ExpoBanner> findExpoBanner(String targetDay);
}
