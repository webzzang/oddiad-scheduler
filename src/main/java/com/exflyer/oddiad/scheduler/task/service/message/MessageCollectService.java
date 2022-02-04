package com.exflyer.oddiad.scheduler.task.service.message;

import com.exflyer.oddiad.scheduler.share.LocalDateUtils;
import com.exflyer.oddiad.scheduler.task.codes.ChannelTypeCode;
import com.exflyer.oddiad.scheduler.task.codes.TargetGroupCode;
import com.exflyer.oddiad.scheduler.task.config.NotificationConfig;
import com.exflyer.oddiad.scheduler.task.dto.NotificationCollectDto;
import com.exflyer.oddiad.scheduler.task.dto.NotificationTargetDto;
import com.exflyer.oddiad.scheduler.task.model.Notification;
import com.exflyer.oddiad.scheduler.task.repository.MessageCollectMapper;
import com.exflyer.oddiad.scheduler.task.repository.NotificationRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageCollectService {


  @Autowired
  private MessageCollectMapper messageCollectMapper;

  @Autowired
  private NotificationRepository notificationRepository;

  @Autowired
  private NotificationConfig notificationConfig;


  public void collectTarget() {
    String sendTime = LocalDateUtils.korNowDateTimeYYYYMMDDHHmmss();
    List<NotificationCollectDto> collectDtoList = messageCollectMapper.findTarget(sendTime);
    for (NotificationCollectDto notificationCollectDto : collectDtoList) {
      TargetGroupCode targetGroupCode = TargetGroupCode.findByCode(notificationCollectDto.getTargetCode());
      List<NotificationTargetDto> notificationTargetList;
      if (targetGroupCode == TargetGroupCode.ALL_ADVERTISERS) {
        notificationTargetList = messageCollectMapper
          .findAllAdvertisersByAdvAccept(notificationCollectDto.isAdvMessage());
      } else if (targetGroupCode == TargetGroupCode.ALL_ODDI_ADVERTISERS) {
        notificationTargetList = messageCollectMapper
          .findAllAdverByAdvAcceptAndChannelType(notificationCollectDto.isAdvMessage(),
            ChannelTypeCode.ODDI.getCode());
      } else if (targetGroupCode == TargetGroupCode.ALL_SUBWAY_ADVERTISERS) {
        notificationTargetList = messageCollectMapper
          .findAllAdverByAdvAcceptAndChannelType(notificationCollectDto.isAdvMessage(),
            ChannelTypeCode.SUBWAY.getCode());
      } else if (targetGroupCode == TargetGroupCode.ALL_MEMBER) {
        notificationTargetList = messageCollectMapper
          .findAllMemberByAdvAccept(notificationCollectDto.isAdvMessage());
      } else {
        notificationTargetList = messageCollectMapper
          .findByNotificationTarget(notificationCollectDto.getTargetGroupSeq());
      }


      List<Notification> notifications = new ArrayList<>();
      for (NotificationTargetDto notificationTargetDto : notificationTargetList) {
        notifications
          .add(new Notification(notificationCollectDto, notificationTargetDto, notificationConfig.getSenderPhone()));
      }
      log.debug("notification list : {}", notifications);
      notificationRepository.saveAll(notifications);
    }
    messageCollectMapper.updateCreateDone();
  }

}
