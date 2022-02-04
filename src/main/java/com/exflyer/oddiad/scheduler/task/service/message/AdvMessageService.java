package com.exflyer.oddiad.scheduler.task.service.message;

import com.exflyer.oddiad.scheduler.share.LocalDateUtils;
import com.exflyer.oddiad.scheduler.task.codes.AdvAuditCode;
import com.exflyer.oddiad.scheduler.task.codes.AdvSendCode;
import com.exflyer.oddiad.scheduler.task.codes.AlrimTalkTemplateCodes;
import com.exflyer.oddiad.scheduler.task.model.Adv;
import com.exflyer.oddiad.scheduler.task.model.KakaoTemplate;
import com.exflyer.oddiad.scheduler.task.model.Member;
import com.exflyer.oddiad.scheduler.task.model.Notification;
import com.exflyer.oddiad.scheduler.task.model.NotificationGroup;
import com.exflyer.oddiad.scheduler.task.repository.AdvRepository;
import com.exflyer.oddiad.scheduler.task.repository.KakaoTemplateRepository;
import com.exflyer.oddiad.scheduler.task.repository.MemberRepository;
import com.exflyer.oddiad.scheduler.task.repository.NotificationRepository;
import com.exflyer.oddiad.scheduler.task.service.notification.NotificationService;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class AdvMessageService {

  @Autowired
  private AdvRepository advRepository;

  @Autowired
  private NotificationRepository notificationRepository;

  @Autowired
  private KakaoTemplateRepository templateRepository;

  @Autowired
  private NotificationService notificationService;

  @Autowired
  private MemberRepository memberRepository;



  public void sendToMemberAcceptState() {
    List<Adv> targetAdv = advRepository
      .findByAuditCodeAndSendCode(AdvAuditCode.ACCEPT.getCode(), AdvSendCode.READY.getCode(), AdvSendCode.DENY.getCode());
    if (CollectionUtils.isEmpty(targetAdv)) {
      return;
    }

    KakaoTemplate kakaoTemplate = getKakaoTemplate(AlrimTalkTemplateCodes.ACCEPTED_ADV);
    if (kakaoTemplate == null) {
      return;
    }

    List<Notification> notificationList = createNotificationList(targetAdv, kakaoTemplate, AdvSendCode.ACCEPT);
    notificationRepository.saveAll(notificationList);
    advRepository.saveAll(targetAdv);
  }

  public void sendToMemberDenyState() {
    List<Adv> targetAdv = advRepository
      .findByAuditCodeAndSendCode(AdvAuditCode.DENY.getCode(), AdvSendCode.READY.getCode(), AdvSendCode.ACCEPT.getCode());
    if (targetAdv == null) {
      return;
    }

    KakaoTemplate kakaoTemplate = getKakaoTemplate(AlrimTalkTemplateCodes.DENY_ADV);
    if (kakaoTemplate == null) {
      return;
    }
    List<Notification> notificationList = createNotificationList(targetAdv, kakaoTemplate, AdvSendCode.DENY);
    notificationRepository.saveAll(notificationList);
    advRepository.saveAll(targetAdv);
  }

  public void sendToMemberWillDone() {
    String targetDate = LocalDateUtils.korNowDate().plusDays(20)
      .format(DateTimeFormatter.ofPattern(LocalDateUtils.YYYYMMDD));
    List<Adv> targetAdv = advRepository.findByAuditCodeAndEndDate(AdvAuditCode.PAY_DONE.getCode(), targetDate);
    if (targetAdv == null) {
      return;
    }

    KakaoTemplate kakaoTemplate = getKakaoTemplate(AlrimTalkTemplateCodes.EXPIRED_ADV);
    if (kakaoTemplate == null) {
      return;
    }
    List<Notification> notificationList = createNotificationList(targetAdv, kakaoTemplate, AdvSendCode.WILL_EXPIRED);
    notificationRepository.saveAll(notificationList);
    advRepository.saveAll(targetAdv);
  }

  private List<Notification> createNotificationList(List<Adv> targetAdv, KakaoTemplate kakaoTemplate, AdvSendCode advSendCode) {
    List<Notification> notificationList = new ArrayList<>();
    NotificationGroup acceptedAdvNotiGroup = notificationService.createGroup(kakaoTemplate, targetAdv.size());
    for (Adv adv : targetAdv) {
      Optional<Member> memberOptional = memberRepository.findById(adv.getMemberId());
      if (memberOptional.isEmpty()) {
        continue;
      }
      Member member = memberOptional.get();
      Notification notification = notificationService.createAdv(member, kakaoTemplate, adv, acceptedAdvNotiGroup);
      if (notification != null) {
        notificationList.add(notification);
        adv.setSendCode(advSendCode.getCode());
      }
    }
    return notificationList;
  }


  private KakaoTemplate getKakaoTemplate(AlrimTalkTemplateCodes alrimTalkTemplateCodes) {
    Optional<KakaoTemplate> kakaoTemplateOptional = templateRepository
      .findById(alrimTalkTemplateCodes.getTemplateId());
    if (!kakaoTemplateOptional.isPresent()) {
      return null;
    }
    KakaoTemplate kakaoTemplate = kakaoTemplateOptional.get();
    return kakaoTemplate;
  }


}
