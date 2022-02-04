package com.exflyer.oddiad.scheduler.task.service.adv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "app.scheduling.enable=false")
public class AdvHistoryServiceTest {

    @Autowired
    private AdvHistoryService advHistoryService;

    @DisplayName("광고 시작 이력")
    @Test
    public void advHistoryTest() throws Exception {
        advHistoryService.advStartHistory();
    }
}
