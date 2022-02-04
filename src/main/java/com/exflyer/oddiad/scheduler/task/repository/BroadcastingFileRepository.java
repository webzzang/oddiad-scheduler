package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.model.BroadcastingFile;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BroadcastingFileRepository extends JpaRepository<BroadcastingFile, Long>,
  JpaSpecificationExecutor<BroadcastingFile> {

  @Modifying
  @Transactional
  @Query(value = "delete from broadcasting_file where reg_date < :regDate", nativeQuery = true)
  void deleteByLesThanBroadcastingModDate(LocalDateTime regDate);

}
