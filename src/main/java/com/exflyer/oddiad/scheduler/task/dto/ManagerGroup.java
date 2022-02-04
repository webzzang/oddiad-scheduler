package com.exflyer.oddiad.scheduler.task.dto;

import lombok.Data;

@Data
public class ManagerGroup {

    private Long roleSeq;

    private String managerId;

    private String managerName;

    private String phoneNumber;

    private String name;

    private String deviceStateCode;

    private String codeName;

    private Boolean managerPush;

    private String regDate;

    private int regDateAdd;

}
