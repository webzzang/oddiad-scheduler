package com.exflyer.oddiad.scheduler.task.codes;

import lombok.Getter;

@Getter
public enum AdvCode {
    ADT_SUCCESS("ADT002", "ADT", "승인"),
    PGT_SUCCESS("PGT002", "PGT", "결제 완료");

    private String code;

    private String groupCode;

    private String name;

    AdvCode(String code, String groupCode, String name) {
        this.code = code;
        this.groupCode = groupCode;
        this.name = name;
    }
}
