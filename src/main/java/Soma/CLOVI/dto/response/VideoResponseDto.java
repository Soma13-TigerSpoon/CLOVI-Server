package Soma.CLOVI.dto.response;

import Soma.CLOVI.domain.TimeFrame;
import Soma.CLOVI.domain.youtube.Video;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.Getter;

@Getter
public class VideoResponseDto {
  private Long id;
  private String creator;
  private String profileImgUrl;
  private String videoUrl;
  private List<TimeShopItemResponseDto> timeShopItemLists = new ArrayList<>();

  public VideoResponseDto(Video video) {
    this.id = video.getId();
    this.creator = video.getChannel().getName(); // select channel -> name
    this.profileImgUrl = video.getChannel().getProfileImgUrl(); // select channel -> profile image url
    this.videoUrl = video.getVideoUrl(); // select video url

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

    return Long.compare(orderA, orderB);
  }
}