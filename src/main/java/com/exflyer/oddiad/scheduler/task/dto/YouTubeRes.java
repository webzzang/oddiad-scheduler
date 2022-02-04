package com.exflyer.oddiad.scheduler.task.dto;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

@Data
public class YouTubeRes {

  @SerializedName("pageInfo")
  private Pageinfo pageinfo;

  @SerializedName("items")
  private List<Items> items;

  @SerializedName("nextPageToken")
  private String nextpagetoken;

  @Data
  public class Pageinfo {

    @SerializedName("resultsPerPage")
    private int resultsperpage;
    @SerializedName("totalResults")
    private int totalresults;
  }

  @Data
  public class Items {

    @SerializedName("snippet")
    private Snippet snippet;

    @SerializedName("id")
    private IdInfo id;
  }

  @Data
  public class Snippet {

    @SerializedName("resourceId")
    private Resourceid resourceid;
    @SerializedName("thumbnails")
    private Thumbnails thumbnails;
    @SerializedName("description")
    private String description;
    @SerializedName("title")
    private String title;
    @SerializedName("publishedAt")
    private String publishedat;
    @SerializedName("position")
    private String position;
  }

  @Data
  public class Resourceid {
    @SerializedName("videoId")
    private String videoid;
  }

  @Data
  public class Thumbnails {
    @SerializedName("high")
    private High high;
  }

  @Data
  public class High {
    @SerializedName("url")
    private String url;
  }

  @Data
  public class IdInfo {

    private String kind;

    private String videoId;
  }
}
