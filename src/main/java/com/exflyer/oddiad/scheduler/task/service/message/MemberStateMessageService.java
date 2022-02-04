package com.exflyer.oddiad.scheduler.task.service.message;

import com.exflyer.oddiad.scheduler.share.LocalDateUtils;
import com.exflyer.oddiad.scheduler.task.codes.AlrimTalkTemplateCodes;
import com.exflyer.oddiad.scheduler.task.codes.MemberStateCode;
import com.exflyer.oddiad.scheduler.task.model.KakaoTemplate;
import com.exflyer.oddiad.scheduler.task.model.Member;
import com.exflyer.oddiad.scheduler.task.model.Notification;
import com.exflyer.oddiad.scheduler.task.model.NotificationGroup;
import com.exflyer.oddiad.scheduler.task.repository.KakaoTemplateRepository;
import com.exflyer.oddiad.scheduler.task.repository.MemberRepository;
import com.exflyer.oddiad.scheduler.task.repository.NotificationRepository;
import com.exflyer.oddiad.scheduler.task.service.notification.NotificationService;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@Slf4j
public class MemberStateMessageService {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private NotificationRepository notificationRepository;

  @Autowired
  private KakaoTemplateRepository kakaoTemplateRepository;

  @Autowired
  private NotificationService notificationService;

  public void sendToMemberWillSleep() {
    String sleepMemberTargetDate = LocalDateUtils.korNowDateTime().minusMonths(11L).format(DateTimeFormatter.ofPattern(LocalDateUtils.YYYYMMDD));
    List<Member> sleepMemberList = memberRepository.findByLoginDate(sleepMemberTargetDate);
    if (CollectionUtils.isEmpty(sleepMemberList)) {
      return;
    }
    Optional<KakaoTemplate> templateOptional = kakaoTemplateRepository
      .findById(AlrimTalkTemplateCodes.SLEEP_ACCOUNT.getTemplateId());
    if (!templateOptional.isPresent()) {
      log.info("!!! sleep alrimtalk template null");
      return;
    }
    KakaoTemplate kakaoTemplate = templateOptional.get();
    NotificationGroup sleepNotificationGroup = notificationService.createGroup(kakaoTemplate, sleepMemberList.size());
    List<Notification> sleepNotification = new ArrayList<>();
    for (Member member : sleepMemberList) {
      Notification notification = notificationService.createByMemberState(member, kakaoTemplate, sleepNotificationGroup);
      sleepNotification.add(notification);
    }
    notificationRepository.saveAll(sleepNotification);
    sleepNotificationGroup.setDone(true);
  }

  public void sendToMemberWillWithdrawal() {
    String withdrawalTargetDate = LocalDateUtils.korNowDate().minusYears(1L).minusMonths(11).format(DateTimeFormatter.ofPattern(LocalDateUtils.YYYYMMDD));
    List<Member> withdrawalTargetList =
      memberRepository.findByStateCodeAndLoginDate(MemberStateCode.SLEEP.getCode(), withdrawalTargetDate);
    if (CollectionUtils.isEmpty(withdrawalTargetList)) {
      return;
    }
    Optional<KakaoTemplate> templateOptional = kakaoTemplateRepository
      .findById(AlrimTalkTemplateCodes.WITHDRAWAL_ACCOUNT.getTemplateId());
    if (!templateOptional.isPresent()) {
      log.info("!!! withdrawal alrimtalk template null");
      return;
    }
    KakaoTemplate kakaoTemplate = templateOptional.get();
    NotificationGroup sleepNotificationGroup = notificationService.createGroup(kakaoTemplate, withdrawalTargetList.size());
    List<Notification> sleepNotification = new ArrayList<>();
    for (Member member : withdrawalTargetList) {
      Notification notification = notificationService.createByMemberState(member, kakaoTemplate, sleepNotificationGroup);
      sleepNotification.add(notification);
    }
    notificationRepository.saveAll(sleepNotification);
  }
}
