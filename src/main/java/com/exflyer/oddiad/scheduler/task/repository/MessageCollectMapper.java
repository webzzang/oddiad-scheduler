package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.dto.NotificationCollectDto;
import com.exflyer.oddiad.scheduler.task.dto.NotificationTargetDto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageCollectMapper {

  List<NotificationCollectDto> findTarget(String sendTime);

  List<NotificationTargetDto> findAllMemberByAdvAccept(boolean advMessage);

  void updateCreateDone();

  List<NotificationTargetDto> findAllAdvertisersByAdvAccept(boolean advMessage);

  List<NotificationTargetDto> findAllAdverByAdvAcceptAndChannelType(boolean advMessage, String channelType);


  List<NotificationTargetDto> findByNotificationTarget(long targetGroupSeq);
}
