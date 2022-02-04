package com.exflyer.oddiad.scheduler.task.jobs.device;

import com.exflyer.oddiad.scheduler.task.service.device.DeviceStateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Slf4j
public class DeviceStateTask {

    @Autowired
    private DeviceStateService deviceStateService;

    /**
     * 장비 모니터링 심각일경우 관리자그룹에 알림톡 전송
     * 1. 장비 심각일경우 pick
     * 2. 관리자 계정(소속그룹 물어보기) select
     * 3. 알림톡 즉시발송
     *
     */
    //@Scheduled(fixedDelay = 5 * TaskScheduleTime.MIN)
    public void deviceState(){
        StopWatch stopWatch = new StopWatch("##### 장비 모니터링");
        stopWatch.start();
        deviceStateService.deviceState();
        stopWatch.stop();
        log.info("{}", stopWatch.shortSummary());
    }
}
