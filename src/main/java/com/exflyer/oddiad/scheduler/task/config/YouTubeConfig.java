package com.exflyer.oddiad.scheduler.task.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class YouTubeConfig {

    @Value("${youtube.apiUrl}")
    private String apiUrl;

    @Value("${youtube.apiKey}")
    private String apiKey;


}
