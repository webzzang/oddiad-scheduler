package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.model.WithdrawalMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WithdrawalMemberRepository extends JpaRepository<WithdrawalMember, String>,
  JpaSpecificationExecutor<WithdrawalMember> {

}
