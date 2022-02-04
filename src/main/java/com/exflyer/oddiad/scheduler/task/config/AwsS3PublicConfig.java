package com.exflyer.oddiad.scheduler.task.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class AwsS3PublicConfig {

  @Value("${aws.s3.public.bucket}")
  private String bucket;

  @Value("${aws.s3.public.video-dir}")
  private String videoDir;

  @Value("${aws.s3.public.url-host}")
  private String urlHost;

  @Value("${aws.s3.public.region}")
  private String region;

}
