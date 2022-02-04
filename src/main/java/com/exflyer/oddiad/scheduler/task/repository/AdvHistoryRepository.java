package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.model.AdvHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdvHistoryRepository extends JpaRepository<AdvHistory, Long>,
    JpaSpecificationExecutor<AdvHistory> {

}
