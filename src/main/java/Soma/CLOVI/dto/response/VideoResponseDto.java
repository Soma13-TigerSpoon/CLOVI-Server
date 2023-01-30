package Soma.CLOVI.dto.response;

import Soma.CLOVI.domain.TimeFrame;
import Soma.CLOVI.domain.youtube.Video;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.Getter;

@Getter
public class VideoResponseDto {
  private Long id;
  private String title;
  private String creator;
  private String profileImgUrl;
  private String videoUrl;
  private String uploadDate;
  private List<TimeShopItemResponseDto> timeShopItemLists = new ArrayList<>();

  public VideoResponseDto(Video video) {
    this.id = video.getId();
    this.title = video.getTitle(); // select video title
    this.creator = video.getChannel().getName(); // select channel -> name
    this.profileImgUrl = video.getChannel().getProfileImgUrl(); // select channel -> profile image url
    this.videoUrl = video.getYoutubeVideoId(); // select video url
    this.uploadDate = video.getUploadDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
    List<TimeFrame> timeFrames = video.getTimeFrames(); // select timeFrames
    if(!timeFrames.isEmpty()) {
      for(TimeFrame timeFrame : video.getTimeFrames()) {
        this.timeShopItemLists.add(new TimeShopItemResponseDto(timeFrame));
      }
      timeShopItemLists.sort(new TimeOrderComparator());
    }
  }
}

class TimeOrderComparator implements Comparator<TimeShopItemResponseDto> {
  @Override
  public int compare(TimeShopItemResponseDto A, TimeShopItemResponseDto B) {
    Long orderA = A.getTimes().getStart();
    Long orderB = B.getTimes().getStart();

    if(orderA == null || orderB == null) return 0;

    return Long.compare(orderA, orderB);
  }
}