package com.exflyer.oddiad.scheduler.task.service.device;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "app.scheduling.enable=false")
public class DeviceStateServiceTest {

    @Autowired
    private DeviceStateService deviceStateService;

    @DisplayName("광고 모니터링")
    @Test
    public void deviceStateTest() throws Exception {
        deviceStateService.deviceState();
    }
}
