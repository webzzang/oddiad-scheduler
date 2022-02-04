package com.exflyer.oddiad.scheduler.task.service.weather;

import com.exflyer.oddiad.scheduler.task.config.WeatherConfig;
import com.exflyer.oddiad.scheduler.task.dto.WeatherData;
import com.exflyer.oddiad.scheduler.task.dto.WeatherResponse;
import com.exflyer.oddiad.scheduler.task.model.Partner;
import com.exflyer.oddiad.scheduler.task.model.Weather;
import com.exflyer.oddiad.scheduler.task.repository.PartnerRepository;
import com.exflyer.oddiad.scheduler.task.repository.WeatherRepository;
import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WeatherService {

  @Autowired
  private WeatherConfig weatherConfig;

  @Autowired
  private HttpClient httpClient;

  @Autowired
  private WeatherRepository weatherRepository;

  @Autowired
  private PartnerRepository partnerRepository;


  public void updateWeather() throws Exception {

    // https://www.kma.go.kr/wid/queryDFS.jsp?gridx=59&gridy=127 를 통해 당일 날씨를 조회
    // partner 조회
    List<Partner> partnerList = partnerRepository.findAll();
    List<Weather> weathers = getWeather(partnerList);
    weatherRepository.saveAll(weathers);
  }

  private List<Weather> getWeather(List<Partner> partnerList) throws Exception {
    List<Weather> weatherList = new ArrayList<>();
    for (Partner partner : partnerList) {
      if (partner.getGridX() != null && partner.getGridY() != null) {

        HttpGet httpGet = new HttpGet(weatherConfig.getDfsApi());

        URI uri = new URIBuilder(httpGet.getURI())
          .addParameter("gridx", String.valueOf(partner.getGridX()))
          .addParameter("gridy", String.valueOf(partner.getGridY()))
          .build();
        httpGet.setURI(uri);
        HttpResponse execute = httpClient.execute(httpGet);

        String response = EntityUtils.toString(execute.getEntity());

        try {
          JAXBContext jaxbContext = JAXBContext.newInstance(WeatherResponse.class);
          Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
          WeatherResponse weatherResponse = (WeatherResponse) unmarshaller.unmarshal(new StringReader(response));
          log.debug("{}", weatherResponse);
          for (WeatherData weatherData : weatherResponse.getWeatherBody().getWeatherData()) {
            Weather weather = new Weather(weatherData, partner);
            Weather weatherInDb = weatherRepository.findByGridXAndGridYAndFcstDateAndFcstTime(
              String.valueOf(partner.getGridX()), String.valueOf(partner.getGridY()),
              weather.getWeatherPk().getFcstDate(), weather.getWeatherPk().getFcstTime()
            );
            if (weatherInDb != null) {
              weather.setTmxAndTmn(weatherInDb);
            }

            weatherList.add(weather);
          }

        } catch (Exception e) {
          log.error("weather exception", e);
        }
        EntityUtils.consumeQuietly(execute.getEntity());
      }
    }
    return weatherList;
  }
}
