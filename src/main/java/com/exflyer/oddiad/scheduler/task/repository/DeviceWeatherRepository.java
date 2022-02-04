package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.model.DeviceWeather;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface DeviceWeatherRepository extends JpaRepository<DeviceWeather, String>,
  JpaSpecificationExecutor<DeviceWeather> {

  @Query(value = "select pd.device_id, "
    + "       concat(p.addr_si, ' ', p.addr_gu, ' ', p.addr_dong) as addr, "
    + "       p.addr  as origin_addr, "
    + "       w.tmp, "
    + "       w.tmn, "
    + "       w.tmx, "
    + "       w.pop, "
    + "       w.reh, "
    + "       '' as pm10, "
    + "       '' as pm10_grade, "
    + "       '' as pm25, "
    + "       '' as pm25_grade, "
    + "       w.wsd, "
    + "       w.weather_code as sky, "
    + "       w.weather_icon as icon "
    + "from partner_device pd, "
    + "     partner p, "
    + "     weather w "
    + "where pd.partner_seq = p.seq "
    + "  and p.grid_x = w.grid_x "
    + "  and p.grid_y = w.grid_y "
    + "  and fcst_date = :searchDate "
    + "  and fcst_time = :searchTime "
    + "group by device_id", nativeQuery = true)
  List<DeviceWeather> findWeather(@Param("searchDate") String searchDate, @Param("searchTime") String searchTime);
  
}
