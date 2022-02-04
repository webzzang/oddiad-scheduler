package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.model.DeviceWeather;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceWeatherMapper {

  List<DeviceWeather> findWeather(String searchDate, String searchTime, String deviceId);
}
