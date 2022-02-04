package com.exflyer.oddiad.scheduler.task.jobs.message;

import com.exflyer.oddiad.scheduler.task.codes.TaskScheduleTime;
import com.exflyer.oddiad.scheduler.task.service.message.MessageCollectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageTargetGroupCollector {

  @Autowired
  private MessageCollectService messageCollectService;


  @Scheduled(fixedDelay = 10 * TaskScheduleTime.MIN)
  public void collectMessageTargetGroup(){
    messageCollectService.collectTarget();
  }


}
