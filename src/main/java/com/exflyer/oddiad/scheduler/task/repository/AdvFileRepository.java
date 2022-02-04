package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.model.AdvFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdvFileRepository extends JpaRepository<AdvFile, Long>, JpaSpecificationExecutor<AdvFile> {


}
