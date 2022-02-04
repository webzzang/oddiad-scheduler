package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.model.Voc;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VocRepository extends JpaRepository<Voc, Long>, JpaSpecificationExecutor<Voc> {

  List<Voc> findBySendDoneAndAnswerRegDateIsNotNull(boolean sendDone);



}
