package com.exflyer.oddiad.scheduler.task.dto;

import lombok.Data;

@Data
public class MediaConvertResult {

    private String filePath;

    public MediaConvertResult(String filePath) {
        this.filePath = filePath;
    }
}
