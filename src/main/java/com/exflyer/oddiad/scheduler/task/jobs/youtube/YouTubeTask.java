package com.exflyer.oddiad.scheduler.task.jobs.youtube;

import com.exflyer.oddiad.scheduler.task.codes.TaskScheduleTime;
import com.exflyer.oddiad.scheduler.task.service.youtube.YouTubeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class YouTubeTask {

  @Autowired
  private YouTubeService youTubeService;

  @Scheduled(fixedDelay = 1 * TaskScheduleTime.HOUR)
  public void savePlayItem() throws Exception {
    youTubeService.savePlayItem();
  }

}
