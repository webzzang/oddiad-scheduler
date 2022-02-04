package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.model.ManagerNotification;
import com.exflyer.oddiad.scheduler.task.model.Notification;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ManagerNotificationRepository extends JpaRepository<ManagerNotification, Long>,
    JpaSpecificationExecutor<ManagerNotification> {

    @Modifying
    @Transactional
    @Query(value = "update manager_notification "
                    + "set reg_date = :regDate, manager_push = true\n"
                    + "where role_seq = :roleSeq and  device_state_code = :deviceStateCode", nativeQuery = true)
    void updateManagerPush(Long roleSeq, String deviceStateCode, LocalDateTime regDate);
}
