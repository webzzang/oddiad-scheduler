package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface WeatherRepository extends JpaRepository<Weather, String>, JpaSpecificationExecutor<Weather> {


  @Query(value = "select * from weather where grid_x = :gridX and grid_y = :gridY and fcst_date = :fcstDate and fcst_time = :fcstTime", nativeQuery = true)
  Weather findByGridXAndGridYAndFcstDateAndFcstTime(String gridX, String gridY, String fcstDate, String fcstTime);

}
