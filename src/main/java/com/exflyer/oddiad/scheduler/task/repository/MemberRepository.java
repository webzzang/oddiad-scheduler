package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.model.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, String>, JpaSpecificationExecutor<Member> {

  @Query(value = "select * from member where date_format(login_date, '%Y%m%d') = :loginDate", nativeQuery = true)
  List<Member> findByLoginDate(String loginDate);

  @Query(value = "select * from member where state_code = :code and date_format(login_date, '%Y%m%d') = :loginDate", nativeQuery = true)
  List<Member> findByStateCodeAndLoginDate(String code, String loginDate);
}
