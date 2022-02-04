package com.exflyer.oddiad.scheduler.task.dto;

import com.exflyer.oddiad.scheduler.share.LocalDateUtils;
import com.exflyer.oddiad.scheduler.task.codes.AdvCode;
import lombok.Data;

@Data
public class AdvInfoReq {

    private String startDate;

    private String auditCode;

    private String progressCode;

    public AdvInfoReq() {
        this.startDate = LocalDateUtils.korNowDateTimeYYYYMMDD();
        this.auditCode = AdvCode.ADT_SUCCESS.getCode();
        this.progressCode = AdvCode.PGT_SUCCESS.getCode();
    }

}
