package com.exflyer.oddiad.scheduler.task.jobs.adv;

import com.exflyer.oddiad.scheduler.task.codes.TaskScheduleTime;
import com.exflyer.oddiad.scheduler.task.service.adv.AdvHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Slf4j
public class AdvHistoryTask {

    @Autowired
    private AdvHistoryService advHistoryService;

    /**
     * 광고 시작 이력
     * - 목적 : 광고 시작되는 건에 대해 이력을 남긴다.
     * - 조건 :
     *  심사 : 광고승인(ADT002)
     *  진행코드 : 결제완료(PGT002)
     * - 주기 : 새벽 1시
     * - Task Class : AdvHistoryTask.java
     */
    @Scheduled(cron = "${schedule.adv.history}")
    public void advStartHistory(){
        StopWatch stopWatch = new StopWatch("##### 광고 시작 이력");
        stopWatch.start();
        advHistoryService.advStartHistory();
        stopWatch.stop();
        log.info("{}", stopWatch.shortSummary());
    }

}
