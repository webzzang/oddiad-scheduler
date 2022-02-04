package com.exflyer.oddiad.scheduler.task.service.device;

import com.exflyer.oddiad.scheduler.task.config.DeviceApiConfig;
import com.exflyer.oddiad.scheduler.task.dto.DevicePushReq;
import com.exflyer.oddiad.scheduler.task.repository.BroadcastingMapper;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DevicePushService {

  @Autowired
  private HttpClient httpClient;

  @Autowired
  private BroadcastingMapper broadcastingMapper;

  @Autowired
  private Gson gson;

  @Autowired
  private DeviceApiConfig deviceApiConfig;

  private final String changed_main_ad = "request_main_ad";
  private final String new_device_mapping = "device_create";


  public void changeBroadcasting(){
    List<String> deviceList = broadcastingMapper.findDevice();
    pushDevice(deviceList, changed_main_ad);
  }

  public void pushDevice(List<String> deviceList, String action) {
    for (String deviceId : deviceList) {
      try {
        DevicePushReq devicePushReq = new DevicePushReq();
        devicePushReq.setDevice_id(deviceId);
        devicePushReq.setAction(action);

        String reqStringBody = gson.toJson(devicePushReq);
        log.debug(">>>> push reqBody : {}", reqStringBody);
        HttpPost httpPost = new HttpPost(deviceApiConfig.getDeviceApiHost() + "/devicePush");
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        httpPost.setEntity(new StringEntity(reqStringBody, "UTF-8"));
        HttpResponse response = httpClient.execute(httpPost);

        String resBody = EntityUtils.toString(response.getEntity());
        log.debug("<<<< push resBody : {}", resBody);

      } catch (IOException e) {
        log.error("!!! broadcasting device push error", e);
      }
    }
  }

  public void pushNewMappingDevice(List<String> deviceList){
   pushDevice(deviceList, new_device_mapping);
  }
}
