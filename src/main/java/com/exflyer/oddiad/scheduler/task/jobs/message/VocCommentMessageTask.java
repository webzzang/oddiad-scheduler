package com.exflyer.oddiad.scheduler.task.jobs.message;

import com.exflyer.oddiad.scheduler.task.codes.TaskScheduleTime;
import com.exflyer.oddiad.scheduler.task.service.message.VocMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Slf4j
public class VocCommentMessageTask {

  @Autowired
  private VocMessageService vocMessageService;

  /**
   * ### 1:1 문의 답변 완료 메세지 발송
   *
   * - 목적 : 관리자가 1:1 문의 답변을 작성 하였을  경우 사용자에게 알림 메세지를 전달 한다.
   *
   * - 조건
   *   - 발송 되지 않았음
   *   - 답변 등록 일자가 있을 경우
   * - 주기 : 5분 마다
   * - Task Class : VocCommentMessageTask.java
   */
  @Scheduled(fixedDelay = 5 * TaskScheduleTime.MIN)
  public void sendVocCommentMessage()  {
    StopWatch sendVocCommentMessageStopWatch = new StopWatch("##### 1:1 문의 답변 완료 메세지 전송");
    sendVocCommentMessageStopWatch.start();
    try {
      vocMessageService.sendToMemberVocComment();
    } catch (Exception e) {
      log.error("send to member will withdrawal error {}", e);
    }
    sendVocCommentMessageStopWatch.stop();
    log.info("{}", sendVocCommentMessageStopWatch.shortSummary());
  }

}
