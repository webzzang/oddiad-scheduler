package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.dto.DeviceStat;
import com.exflyer.oddiad.scheduler.task.dto.ManagerGroup;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceStatMapper {

    List<DeviceStat> findDeviceState(LocalDateTime nowDate);
    List<ManagerGroup> findManagerGroup(String state, String deviceStateCode,LocalDateTime nowDate);
}
