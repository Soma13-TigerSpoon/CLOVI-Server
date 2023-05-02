package com.clovi.app.video.dto.response;

import com.clovi.app.timeShopItem.dto.response.TimeShopItemResponse;
import com.clovi.app.timeframe.Timeframe;
import com.clovi.app.video.Video;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import lombok.Getter;

@Getter
public class VideoResponse {
  private Long id;
  private String title;
  private String creator;
  private String profileImgUrl;
  private String videoUrl;
  private String uploadDate;
  private List<TimeShopItemResponse> timeShopItemLists = new ArrayList<>();

  public VideoResponse(Video video) {
    this.id = video.getId();
    this.title = video.getTitle(); // select video title
    this.creator = video.getChannel().getName(); // select channel -> name
    this.profileImgUrl = video.getChannel().getProfileImgUrl(); // select channel -> profile image url
    this.videoUrl = video.getYoutubeVideoId(); // select video url
    this.uploadDate = video.getUploadDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
    List<Timeframe> timeframes = video.getTimeframes(); // select timeframes
    if(!timeframes.isEmpty()) {
      for(Timeframe timeFrame : video.getTimeframes()) {
        this.timeShopItemLists.add(new TimeShopItemResponse(timeFrame));
      }
      timeShopItemLists.sort(new TimeOrderComparator());
    }
  }
}

class TimeOrderComparator implements Comparator<TimeShopItemResponse> {
  @Override
  public int compare(TimeShopItemResponse A, TimeShopItemResponse B) {
    Long orderA = A.getTime().getStart();
    Long orderB = B.getTime().getStart();

    if(orderA == null || orderB == null) return 0;

    return Long.compare(orderA, orderB);
  }
}