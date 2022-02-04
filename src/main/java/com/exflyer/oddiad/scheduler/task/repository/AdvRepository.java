package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.model.Adv;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface AdvRepository extends JpaRepository<Adv, Long>, JpaSpecificationExecutor<Adv> {

  @Query(value = "select * from adv "
    + "where audit_code = :auditCode "
    + "and send_code in (:sendCode)  ", nativeQuery = true)
  List<Adv> findByAuditCodeAndSendCode(String auditCode, String... sendCode);



  @Query(value = "select * from adv "
    + "where audit_code = :auditCode "
    + "and end_date = :endDate ", nativeQuery = true)
  List<Adv> findByAuditCodeAndEndDate(String auditCode, String endDate);

  List<Adv> findByProgressCodeAndRegDateBetween(String code, LocalDateTime before, LocalDateTime now);
}
