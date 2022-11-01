package Soma.CLOVI.domain.youtube;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.ManyToMany.VideoItem;
import Soma.CLOVI.domain.TimeFrame;
import Soma.CLOVI.domain.user.YoutubeCreator;
import Soma.CLOVI.dto.requests.VideoRequestDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(indexes = {
    @Index(name = "i_title", columnList = "title"),
    @Index(name = "i_video_url", columnList = "videoUrl")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Video extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @Column(unique = true, nullable = false)
  private String videoUrl;


  private Long length;

  private LocalDateTime uploadDate;

  @ManyToOne(fetch = FetchType.LAZY)
  private Channel channel;

  @ManyToOne(fetch = FetchType.LAZY)
  private YoutubeCreator youtubeCreator;

  @OneToMany(mappedBy = "video", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<VideoItem> videoItems = new ArrayList<>();

  @OneToMany(mappedBy = "video", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<TimeFrame> timeFrames = new ArrayList<>();

  public Video(String title, String videoUrl, Long length, YoutubeCreator youtubeCreator,
      Channel channel) {
    this.title = title;
    this.videoUrl = videoUrl;
    this.length = length;
    this.youtubeCreator = youtubeCreator;
    this.channel = channel;
  }

  public Video(VideoRequestDto videoRequestDto, Channel channel) {
    this.title = videoRequestDto.getVideoTitle();
    this.videoUrl = videoRequestDto.getVideoUrlId();
    this.length = videoRequestDto.getVideoLength();
    this.youtubeCreator = channel.getYoutubeCreator();
    this.channel = channel;
  }

  public void addTimeFrame(TimeFrame timeFrame) {
    this.timeFrames.add(timeFrame);
  }

  public void addVideoItem(VideoItem videoItem) {
    this.videoItems.add(videoItem);
  }

}
