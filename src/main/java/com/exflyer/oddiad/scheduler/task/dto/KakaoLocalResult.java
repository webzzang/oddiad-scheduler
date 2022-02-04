package com.exflyer.oddiad.scheduler.task.dto;

import java.util.List;
import lombok.Data;

@Data
public class KakaoLocalResult {


  private Meta meta;
  private List<Documents> documents;

  @Data
  public class Meta {
    private int totalCount;
    private int pageableCount;
    private boolean isEnd;
  }


  @Data
  public class Address {
    private Double y;
    private Double x;
    private String subAddressNo;
    private String region3depthName;
    private String region3depthHName;
    private String region2depthName;
    private String region1depthName;
    private String mountainYn;
    private String mainAddressNo;
    private String hCode;
    private String bCode;
    private String addressName;
  }

  @Data
  public class RoadAddress {
    private String zoneNo;
    private String y;
    private String x;
    private String undergroundYn;
    private String subBuildingNo;
    private String roadName;
    private String region3depthName;
    private String region2depthName;
    private String region1depthName;
    private String mainBuildingNo;
    private String buildingName;
    private String addressName;
  }

  @Data
  public class Documents {
    private String y;
    private String x;
    private RoadAddress roadAddress;
    private String addressType;
    private String addressName;
    private Address address;
  }
}
