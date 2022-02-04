package com.exflyer.oddiad.scheduler.task.jobs.media;

import com.exflyer.oddiad.scheduler.task.codes.TaskScheduleTime;
import com.exflyer.oddiad.scheduler.task.service.media.MediaConvertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Slf4j
public class MediaConvertTask {

  @Autowired
  private MediaConvertService mediaConvertService;


  /**
   * -
   *
   * ### 미디어 컨버터
   *
   * - 목적 : 사용자가 등록한 광고 영상 파일을 스트리밍 할 수 있도록 AWS Media Convertor 를 이용하여 변환 한다.
   *
   * - 조건
   *   - 오디존(PTT001)
   *   - 승인
   *   - 결제완료
   *   - 동영상 광고
   *   -  컨버팅한 결과가 없음
   * - 주기 :  6시간 마다
   * - Task Class : GeographyTask.java
   */
//  @Scheduled(cron = "${schedule.media.convert}")
  @Scheduled(fixedDelay = TaskScheduleTime.HOUR)
  public void convertVideo(){
    StopWatch stopWatch = new StopWatch("##### 미디어 컨버팅");
    stopWatch.start();
    mediaConvertService.convert();
    stopWatch.stop();
    log.info("{}", stopWatch.shortSummary());
    
  }

}
