package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.model.NotificationGroup;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface NotificationGroupRepository extends JpaRepository<NotificationGroup, Long>,
  JpaSpecificationExecutor<NotificationGroup> {

  @Modifying
  @Transactional
  @Query(value = "UPDATE notification_group ng,(select group_seq "
      + "                                   , count(case when response_code = '200' then 1 end)  as success_count "
      + "                                   , count(case when response_code <> '200' then 1 end) as fail_count "
      + "                              from notification_history "
      + "                              group by group_seq) history "
      + "SET ng.success_count = history.success_count, "
      + "    ng.fail_count    = history.fail_count, "
      + "    ng.done    = true "
      + "WHERE ng.seq = history.group_seq "
      + "and ng.reg_date between :startDate and :endDate", nativeQuery = true)
  void updateSuccessAndFailCount(LocalDate startDate, LocalDate endDate);


  @Modifying
  @Transactional
  @Query(value = "UPDATE notification_group SET total_count = :totalCount WHERE seq = :seq", nativeQuery = true)
  void updateNotificationGroup(Long seq, int totalCount);
}
