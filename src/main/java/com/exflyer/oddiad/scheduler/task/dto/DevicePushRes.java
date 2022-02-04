package com.exflyer.oddiad.scheduler.task.dto;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

@Data
public class DevicePushRes {

    @SerializedName("success")
    private Integer success;

    @SerializedName("failure")
    private Integer failure;

    @SerializedName("canonical_ids")
    private String canonical_ids;

    @SerializedName("results")
    private List<results> results;

    @Data
    public class results {
        @SerializedName("error")
        private String error;
    }

}
