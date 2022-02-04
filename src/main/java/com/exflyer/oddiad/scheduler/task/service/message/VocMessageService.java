package com.exflyer.oddiad.scheduler.task.service.message;

import com.exflyer.oddiad.scheduler.task.codes.AlrimTalkTemplateCodes;
import com.exflyer.oddiad.scheduler.task.model.KakaoTemplate;
import com.exflyer.oddiad.scheduler.task.model.Member;
import com.exflyer.oddiad.scheduler.task.model.Notification;
import com.exflyer.oddiad.scheduler.task.model.NotificationGroup;
import com.exflyer.oddiad.scheduler.task.model.Voc;
import com.exflyer.oddiad.scheduler.task.repository.KakaoTemplateRepository;
import com.exflyer.oddiad.scheduler.task.repository.MemberRepository;
import com.exflyer.oddiad.scheduler.task.repository.NotificationRepository;
import com.exflyer.oddiad.scheduler.task.repository.VocRepository;
import com.exflyer.oddiad.scheduler.task.service.notification.NotificationService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Component
@Slf4j
public class VocMessageService {

  @Autowired
  private VocRepository vocRepository;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private KakaoTemplateRepository kakaoTemplateRepository;


  @Autowired
  private NotificationService notificationService;

  @Autowired
  private NotificationRepository notificationRepository;

  /**
   * 오디AD 알림> #{고객이름} 님 문의에 답변 드립니다.
   *
   * 문의: #{1:1문의 제목} 답변 확인: #{문의글 Url}
   *
   * https://oddiad.com
   */
  @Transactional
  public void sendToMemberVocComment() {
    List<Voc> targetVocList = vocRepository.findBySendDoneAndAnswerRegDateIsNotNull(false);
    if (CollectionUtils.isEmpty(targetVocList)) {
      return;
    }
    KakaoTemplate kakaoTemplate = getVocTemplate();
    if (kakaoTemplate == null) {
      return;
    }
    NotificationGroup vocNotificationGroup = notificationService.createGroup(kakaoTemplate, targetVocList.size());
    List<Notification> vocCommentNotification = new ArrayList<>();
    for (Voc voc : targetVocList) {
      Member vocMember;
      if (StringUtils.isNotBlank(voc.getMemberId())) {
        Optional<Member> vocMemberOptional = memberRepository.findById(voc.getMemberId());
        vocMember = vocMemberOptional.orElse(new Member(voc.getName(), voc.getPhoneNumber()));
      } else {
        vocMember = new Member(voc.getName(), voc.getPhoneNumber());
      }
      Notification vocNotification = notificationService.createVoc(vocMember, voc, kakaoTemplate, vocNotificationGroup);
      vocCommentNotification.add(vocNotification);
      voc.setSendDone(true);
    }
    notificationRepository.saveAll(vocCommentNotification);
    vocRepository.saveAll(targetVocList);
  }

  private KakaoTemplate getVocTemplate() {
    Optional<KakaoTemplate> templateOptional = kakaoTemplateRepository
      .findById(AlrimTalkTemplateCodes.VOC_COMMENT.getTemplateId());
    if (!templateOptional.isPresent()) {
      log.info("!!! voc comment template null");
      return null;
    }
    KakaoTemplate kakaoTemplate = templateOptional.get();
    return kakaoTemplate;
  }


}
