package com.exflyer.oddiad.scheduler.task.dto;

import java.util.List;
import lombok.Data;

@Data
public class DustApiResponse {

  private Response response;

  @Data
  public class Response {

    private Header header;
    private Body body;
  }

  @Data
  public class Header {

    private String resultCode;
    private String resultMsg;
  }

  @Data
  public class Body {

    private int numOfRows;
    private int pageNo;
    private List<Items> items;
    private int totalCount;
  }

  @Data
  public class Items {

    private String o3Value;
    private String pm10Grade;
    private String no2Value;
    private String coGrade;
    private String dataTime;
    private String pm25Grade;
    private String no2Grade;
    private String pm25Value;
    private String khaiGrade;
    private String o3Grade;
    private String pm10Value;
    private String coValue;
    private String so2Value;
    private String khaiValue;
    private String so2Grade;
  }
}
