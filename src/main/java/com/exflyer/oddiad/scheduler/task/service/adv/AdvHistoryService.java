package com.exflyer.oddiad.scheduler.task.service.adv;

import com.exflyer.oddiad.scheduler.task.dto.AdvInfoReq;
import com.exflyer.oddiad.scheduler.task.model.AdvHistory;
import com.exflyer.oddiad.scheduler.task.repository.AdvHistoryMapper;
import com.exflyer.oddiad.scheduler.task.repository.AdvHistoryRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AdvHistoryService {

    @Autowired
    private AdvHistoryMapper advHistoryMapper;

    @Autowired
    private AdvHistoryRepository advHistoryRepository;

    public void advStartHistory() {

        List<AdvHistory> advInfoList = advHistoryMapper.findByAdvStartList(new AdvInfoReq());
        advHistoryRepository.saveAll(advInfoList);
    }
}
