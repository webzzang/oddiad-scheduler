package com.exflyer.oddiad.scheduler.task.service.weather;

import com.exflyer.oddiad.scheduler.share.LocalDateUtils;
import com.exflyer.oddiad.scheduler.task.codes.WeatherTimeRange;
import com.exflyer.oddiad.scheduler.task.model.DeviceWeather;
import com.exflyer.oddiad.scheduler.task.model.Dust;
import com.exflyer.oddiad.scheduler.task.repository.DeviceWeatherMapper;
import com.exflyer.oddiad.scheduler.task.repository.DeviceWeatherRepository;
import com.exflyer.oddiad.scheduler.task.repository.DustRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceWeatherService {

  @Autowired
  private DeviceWeatherRepository deviceWeatherRepository;

  @Autowired
  private DustRepository dustRepository;

  @Autowired
  private DeviceWeatherMapper deviceWeatherMapper;

  public void update() {
    update(null);
  }


  public void update(String deviceId) {
    LocalDateTime now = LocalDateUtils.korNowDateTime();
    String nowDay = now.format(DateTimeFormatter.ofPattern(LocalDateUtils.YYYYMMDD));
    String notTime = now.format(DateTimeFormatter.ofPattern(LocalDateUtils.HH));

    WeatherTimeRange weatherTimeRange = WeatherTimeRange.calcFcstTime(notTime);

    List<DeviceWeather> deviceWeather = deviceWeatherMapper.findWeather(nowDay, weatherTimeRange.getFcstTime(), deviceId);
    for (DeviceWeather weather : deviceWeather) {

      String addr = "";
      if (StringUtils.isBlank(weather.getAddr())) {
        for (int x = 0; x < 3; x++) {
          addr += weather.getOriginAddr().split(" ")[x] + " ";
        }
        weather.setAddr(addr);
      }
      String sido;
      if (StringUtils.isBlank(weather.getSido())) {
        sido = weather.getOriginAddr().split(" ")[0].substring(0, 2);
      }else{
        sido = weather.getSido();
      }
      Dust dust = dustRepository.findBySiName(sido);
      if (dust != null) {
        weather.setPm10(dust.getPm10());
        weather.setPm10Grade(dust.getPm10Grade());
        weather.setPm25(dust.getPm25());
        weather.setPm25Grade(dust.getPm25Grade());
      }
    }
    deviceWeatherRepository.saveAll(deviceWeather);
  }
}
