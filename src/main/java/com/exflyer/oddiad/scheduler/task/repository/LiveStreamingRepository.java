package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.model.LiveStreaming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LiveStreamingRepository extends JpaRepository<LiveStreaming, Void>,
  JpaSpecificationExecutor<LiveStreaming> {

}
