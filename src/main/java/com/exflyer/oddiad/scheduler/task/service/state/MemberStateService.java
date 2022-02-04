package com.exflyer.oddiad.scheduler.task.service.state;

import com.exflyer.oddiad.scheduler.share.LocalDateUtils;
import com.exflyer.oddiad.scheduler.task.codes.MemberStateCode;
import com.exflyer.oddiad.scheduler.task.model.Member;
import com.exflyer.oddiad.scheduler.task.model.WithdrawalMember;
import com.exflyer.oddiad.scheduler.task.repository.MemberRepository;
import com.exflyer.oddiad.scheduler.task.repository.WithdrawalMemberRepository;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Component
public class MemberStateService {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private WithdrawalMemberRepository withdrawalMemberRepository;

  @Transactional
  public void updateSleepStatus() {
    String sleepTargetDate = LocalDateUtils.korNowDateTime().minusYears(1L).format(DateTimeFormatter.ofPattern(LocalDateUtils.YYYYMMDD));
    List<Member> sleepStateMember = memberRepository.findByLoginDate(sleepTargetDate);
    for (Member sleepMember : sleepStateMember) {
      sleepMember.setStateCode(MemberStateCode.SLEEP.getCode());
    }
    memberRepository.saveAll(sleepStateMember);
  }

  @Transactional
  public void updateWithdrawal() {
    String withdrawalTargetDate = LocalDateUtils.korNowDateTime().minusYears(2L).format(DateTimeFormatter.ofPattern(LocalDateUtils.YYYYMMDD));
    List<Member> targetWithdrawal = memberRepository.findByStateCodeAndLoginDate(MemberStateCode.SLEEP.getCode(), withdrawalTargetDate);
    if (CollectionUtils.isEmpty(targetWithdrawal)) {
      return;
    }
    List<WithdrawalMember> withdrawalMemberList = new ArrayList<>();
    for (Member target : targetWithdrawal) {
      WithdrawalMember withdrawalMember = new WithdrawalMember();
      BeanUtils.copyProperties(target, withdrawalMember);
      withdrawalMemberList.add(withdrawalMember);
    }
    withdrawalMemberRepository.saveAll(withdrawalMemberList);
    memberRepository.deleteAll(targetWithdrawal);

  }
}
