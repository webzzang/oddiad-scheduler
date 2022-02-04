package com.exflyer.oddiad.scheduler.task.service.weather;

import com.exflyer.oddiad.scheduler.task.codes.DustGradeCodes;
import com.exflyer.oddiad.scheduler.task.config.DustConfig;
import com.exflyer.oddiad.scheduler.task.dto.DustApiResponse;
import com.exflyer.oddiad.scheduler.task.model.Dust;
import com.exflyer.oddiad.scheduler.task.model.Partner;
import com.exflyer.oddiad.scheduler.task.repository.DustRepository;
import com.exflyer.oddiad.scheduler.task.repository.PartnerRepository;
import com.google.gson.Gson;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DustService {

  @Autowired
  private Gson gson;

  @Autowired
  private HttpClient httpClient;

  @Autowired
  private DustConfig dustConfig;

  @Autowired
  private PartnerRepository partnerRepository;

  @Autowired
  private DustRepository dustRepository;

  public List<Dust> findDustCondition(List<Partner> partnerList) throws Exception {
    List<Dust> dustList = new ArrayList<>();
    for (Partner partner : partnerList) {
      HttpGet httpGet = new HttpGet(dustConfig.getHost() + dustConfig.getApiPath());
      URI uri = new URIBuilder(httpGet.getURI())
        .addParameter("serviceKey", dustConfig.getServiceKey())
        .addParameter("returnType", "JSON")
        .addParameter("numOfRows", "1000")
        .addParameter("pageNo", "1")
        .addParameter("sidoName", partner.getAddrSi())
        .addParameter("dataTerm", "DAILY")
        .addParameter("ver", "1.0")
        .build();
      httpGet.setURI(uri);
      HttpResponse response = httpClient.execute(httpGet);
      String responseBody = EntityUtils.toString(response.getEntity());
      DustApiResponse dustApiResponse = gson.fromJson(responseBody, DustApiResponse.class);
      Dust dust = new Dust();
      dust.setSiName(partner.getAddrSi());
      dust.setPm10(dustApiResponse.getResponse().getBody().getItems().get(0).getPm10Value());
      String pm10Grade = dustApiResponse.getResponse().getBody().getItems().get(0).getPm10Grade();
      DustGradeCodes pm10GradeCode = DustGradeCodes.findByGradeValue(Integer.valueOf(pm10Grade));
      dust.setPm10Grade(pm10GradeCode.getName());
      dust.setPm25(dustApiResponse.getResponse().getBody().getItems().get(0).getPm25Value());
      String pm25Grade = dustApiResponse.getResponse().getBody().getItems().get(0).getPm25Grade();
      DustGradeCodes pm25GradeCode = DustGradeCodes.findByGradeValue(Integer.valueOf(pm25Grade));
      dust.setPm25Grade(pm25GradeCode.getName());
      dustList.add(dust);
      EntityUtils.consumeQuietly(response.getEntity());
    }
    return dustList;
  }


  public void updateDust() throws Exception {
    List<Partner> partnerList = partnerRepository.findAll();
    List<Dust> dustList = findDustCondition(partnerList);
    dustRepository.saveAll(dustList);
  }
}
