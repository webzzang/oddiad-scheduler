package com.exflyer.oddiad.scheduler.task.service.youtube;

import com.exflyer.oddiad.scheduler.share.LocalDateUtils;
import com.exflyer.oddiad.scheduler.task.config.YouTubeConfig;
import com.exflyer.oddiad.scheduler.task.dto.YouTubeRes;
import com.exflyer.oddiad.scheduler.task.dto.YouTubeRes.Items;
import com.exflyer.oddiad.scheduler.task.model.LiveStreaming;
import com.exflyer.oddiad.scheduler.task.model.Youtube;
import com.exflyer.oddiad.scheduler.task.repository.LiveStreamingRepository;
import com.exflyer.oddiad.scheduler.task.repository.YoutubeRepository;
import com.google.gson.Gson;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@Slf4j
public class YouTubeService {

  @Autowired
  private YouTubeConfig youTubeConfig;

  @Autowired
  private HttpClient httpClient;

  @Autowired
  private Gson gson;

  @Autowired
  private YoutubeRepository youTubeRepository;

  @Autowired
  private LiveStreamingRepository liveStreamingRepository;

  private String nextToken = "";

  private String channelId;

  @PostConstruct
  public void setting() {
    List<LiveStreaming> liveStreamingList = liveStreamingRepository.findAll();
    if (CollectionUtils.isEmpty(liveStreamingList)) {
      channelId = "UCUpma3sbzBPxQhWnyUKDBZg";
    } else {
      channelId = liveStreamingList.get(0).getLiveStreamChannelId();
    }

  }

  public void savePlayItem() throws Exception {

    HttpGet httpGet = new HttpGet(youTubeConfig.getApiUrl());

    URI uri = new URIBuilder(httpGet.getURI())
      .addParameter("key", youTubeConfig.getApiKey())
      .addParameter("channelId", channelId)
      .addParameter("pageToken", nextToken)
      .addParameter("part", "snippet")
      .addParameter("type", "video")
      .addParameter("order", "date")
      .addParameter("maxResults", "50")
      .build();
    httpGet.setURI(uri);

    HttpResponse response = httpClient.execute(httpGet);
    String resBody = EntityUtils.toString(response.getEntity());
    log.debug("{}", resBody);
    YouTubeRes youTubeRes = gson.fromJson(resBody, YouTubeRes.class);
    nextToken = youTubeRes.getNextpagetoken();

    saveYouTube(youTubeRes);

    // 재귀 함수....
    if (nextToken != null) {
      savePlayItem();
    }
  }

  private void saveYouTube(YouTubeRes youTubeRes) {
    List<Items> playItems = youTubeRes.getItems();
    if (playItems == null) {
      return;
    }
    playItems.forEach(datas -> {
      Optional<Youtube> youtubeInDb = youTubeRepository.findById(datas.getId().getVideoId());

      if (youtubeInDb.isPresent()) {
        return;
      }

      String publishedAt = datas.getSnippet().getPublishedat();

      Youtube youtube = new Youtube();
      youtube.setYoutubeId(datas.getId().getVideoId());
      youtube.setYoutubeTitle(datas.getSnippet().getTitle());
      youtube.setYoutubeRegDate(publishedAt.substring(0, publishedAt.indexOf("T")) + publishedAt
        .substring(publishedAt.indexOf("T"), publishedAt.indexOf("Z")));
      youtube.setYoutubeDescription(datas.getSnippet().getDescription());
      youtube.setYoutubeThumbnails(datas.getSnippet().getThumbnails().getHigh().getUrl());
      youtube.setYoutubePlayId(datas.getId().getVideoId());
      youtube.setYoutubeUrl("https://www.youtube.com/watch?v=" + datas.getId().getVideoId());
      youtube.setRegDate(LocalDateUtils.korNowDateTime());
      youtube.setExpo(true);
      youTubeRepository.save(youtube);
    });
  }
}
