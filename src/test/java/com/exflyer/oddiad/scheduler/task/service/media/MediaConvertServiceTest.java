package com.exflyer.oddiad.scheduler.task.service.media;

import com.exflyer.oddiad.scheduler.task.config.AwsMediaConfig;
import com.exflyer.oddiad.scheduler.task.dto.MediaConvertResult;
import com.google.gson.Gson;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.mediaconvert.MediaConvertClient;
import software.amazon.awssdk.services.mediaconvert.model.ListJobsRequest;
import software.amazon.awssdk.services.mediaconvert.model.ListJobsResponse;
import software.amazon.awssdk.services.mediaconvert.model.MediaConvertException;
import software.amazon.awssdk.services.mediaconvert.model.Order;

@SpringBootTest
@TestPropertySource(properties = "app.scheduling.enable=false")
@Slf4j
class MediaConvertServiceTest {


  @Autowired
  private AwsMediaConfig awsMediaConfig;


  @Autowired
  private MediaConvertService mediaConvertService;


  @DisplayName("미디어 컨버팅 대상 조회 후 컨버팅")
  @Test
  public void convert() throws Exception {
    mediaConvertService.convert();
  }

  @DisplayName("미디어 컨버팅")
  @Test
  public void convertMedia() throws Exception {
    String testFile = "e83ab6c119c24a07bb60c18670b6bc6c.mp4";
    MediaConvertResult mediaConvertResult = mediaConvertService.convertHls(testFile);
    log.debug("mediaConvertResult : {}", mediaConvertResult);
  }

  @Autowired
  private Gson gson;

  @DisplayName("")
  @Test
  public void getListJobs() throws Exception {

    try {
      MediaConvertClient emc = MediaConvertClient.builder()
        .region(Region.US_WEST_2)
        .endpointOverride(URI.create(awsMediaConfig.getEndPoint()))
        .build();

      ListJobsRequest jobsRequest = ListJobsRequest.builder()
        .maxResults(20)
        .order(Order.DESCENDING)
//        .status("COMPLETE")
        .build();

      ListJobsResponse jobsResponse = emc.listJobs(jobsRequest);
      log.debug("jobsResponse : {}", jobsResponse);

    } catch (MediaConvertException e) {
      System.out.println(e.toString());
      System.exit(0);
    }
  }

}
