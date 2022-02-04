package com.exflyer.oddiad.scheduler.task.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class AwsMediaConfig {

    @Value("${aws.media.host}")
    private String host;

    @Value("${aws.media.role-arn}")
    private String roleArn;

    @Value("${aws.media.file-out-dir}")
    private String fileOutDir;

    @Value("${aws.media.end-point}")
    private String endPoint;

    @Value("${aws.media.region}")
    private String region;
}
