package com.exflyer.oddiad.scheduler.task.service.notification;

import com.exflyer.oddiad.scheduler.task.codes.AlrimTalkTemplateCodes;
import com.exflyer.oddiad.scheduler.task.codes.SchedulerRegCode;
import com.exflyer.oddiad.scheduler.task.config.NotificationConfig;
import com.exflyer.oddiad.scheduler.task.dto.ManagerGroup;
import com.exflyer.oddiad.scheduler.task.model.Adv;
import com.exflyer.oddiad.scheduler.task.model.KakaoTemplate;
import com.exflyer.oddiad.scheduler.task.model.Member;
import com.exflyer.oddiad.scheduler.task.model.Notification;
import com.exflyer.oddiad.scheduler.task.model.NotificationGroup;
import com.exflyer.oddiad.scheduler.task.model.Voc;
import com.exflyer.oddiad.scheduler.task.repository.NotificationGroupRepository;
import com.exflyer.oddiad.scheduler.task.service.message.AlrimtalkVariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {

  @Autowired
  private NotificationConfig notificationConfig;

  @Autowired
  private NotificationGroupRepository notificationGroupRepository;

  @Autowired
  private AlrimtalkVariableService alrimtalkVariableService;

  public NotificationGroup createGroup(KakaoTemplate kakaoTemplate, int totalSize) {
    return createGroup(kakaoTemplate, totalSize, true);
  }

  public NotificationGroup createGroup(KakaoTemplate kakaoTemplate, int totalSize, boolean targetCreateDone) {
    NotificationGroup notificationGroup = new NotificationGroup(SchedulerRegCode.createRegInfo(),
      notificationConfig, kakaoTemplate, totalSize, targetCreateDone);
    notificationGroupRepository.save(notificationGroup);
    return notificationGroup;
  }

  public Notification createVoc(Member member, Voc voc, KakaoTemplate kakaoTemplate,
    NotificationGroup vocNotificationGroup) {
    String content = null;
    AlrimTalkTemplateCodes templateCode = AlrimTalkTemplateCodes.findByCode(kakaoTemplate.getTemplateId());
    if (AlrimTalkTemplateCodes.VOC_COMMENT == templateCode) {
      content = alrimtalkVariableService.convertVocCommentVariable(member.getName(), voc,
        kakaoTemplate.getTemplateContents());
    }
    return new Notification(member, content, vocNotificationGroup, notificationConfig.getSenderPhone());
  }

  public Notification createByMemberState(Member member, KakaoTemplate kakaoTemplate,
    NotificationGroup notificationGroup) {
    String content;
    if (AlrimTalkTemplateCodes.SLEEP_ACCOUNT == AlrimTalkTemplateCodes.findByCode(kakaoTemplate.getTemplateId())) {
      content = alrimtalkVariableService
        .convertSleepMemberVariable(member, kakaoTemplate.getTemplateContents());
    } else {
      content = alrimtalkVariableService
        .convertWithdrawalMemberVariable(member, kakaoTemplate.getTemplateContents());
    }
    return new Notification(member, content, notificationGroup, notificationConfig.getSenderPhone());
  }

  public Notification createAdv(Member member, KakaoTemplate kakaoTemplate, Adv adv,
    NotificationGroup notificationGroup) {

    String content;
    if (AlrimTalkTemplateCodes.ACCEPTED_ADV == AlrimTalkTemplateCodes.findByCode(kakaoTemplate.getTemplateId())) {
      content = alrimtalkVariableService.convertAcceptedAdv(member.getName(), kakaoTemplate.getTemplateContents(), adv);
    } else if (AlrimTalkTemplateCodes.DENY_ADV == AlrimTalkTemplateCodes.findByCode(kakaoTemplate.getTemplateId())) {
      content = alrimtalkVariableService
        .convertDenyAdv(member.getName(), kakaoTemplate.getTemplateContents(), adv);
    } else if (AlrimTalkTemplateCodes.EXPIRED_ADV == AlrimTalkTemplateCodes.findByCode(kakaoTemplate.getTemplateId())) {
      content = alrimtalkVariableService
        .convertWillDoneAdv(member.getName(), kakaoTemplate.getTemplateContents(), adv);
    } else {
      return null;
    }
    return new Notification(member, content, notificationGroup, notificationConfig.getSenderPhone());
  }

  public Notification createDevice(ManagerGroup managerGroup,KakaoTemplate kakaoTemplate,String msg,NotificationGroup notificationGroup) {
    String content = null;
    AlrimTalkTemplateCodes templateCode = AlrimTalkTemplateCodes.findByCode(kakaoTemplate.getTemplateId());
    if (AlrimTalkTemplateCodes.SYS_ALERT == templateCode) {
      content = alrimtalkVariableService.convertDeviceStat(kakaoTemplate.getTemplateContents(), msg);
    }
    return new Notification(managerGroup, content, notificationGroup, notificationConfig.getSenderPhone());
  }

}
