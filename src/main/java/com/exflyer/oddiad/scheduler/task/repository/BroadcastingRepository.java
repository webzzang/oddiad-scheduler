package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.model.Broadcasting;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BroadcastingRepository extends JpaRepository<Broadcasting, Long>,
  JpaSpecificationExecutor<Broadcasting> {

   Optional<Broadcasting> findByDeviceIdAndAdvSeq(String deviceId, Long advSeq);

  @Modifying
  @Transactional
  @Query(value = "delete from broadcasting where mod_date < :modDate", nativeQuery = true)
  void deleteByLesThanBroadcastingModDate(LocalDateTime modDate);


}
