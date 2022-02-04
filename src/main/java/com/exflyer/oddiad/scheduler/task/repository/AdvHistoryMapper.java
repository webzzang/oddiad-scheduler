package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.dto.AdvInfoReq;
import com.exflyer.oddiad.scheduler.task.model.AdvHistory;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvHistoryMapper {

    List<AdvHistory> findByAdvStartList(AdvInfoReq req);
}
