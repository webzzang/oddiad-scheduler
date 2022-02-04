package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.dto.MediaConvertTarget;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaConvertMapper {

  List<MediaConvertTarget> findTarget();

}
