package com.clovi.app.video;

import com.clovi.app.base.domain.BaseTimeEntity;
import com.clovi.app.channel.Channel;
import com.clovi.app.timeframe.Timeframe;
import com.clovi.app.videoItem.VideoItem;

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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(indexes = {
    @Index(name = "i_title", columnList = "title"),
    @Index(name = "i_youtube_video_id", columnList = "youtubeVideoId")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Video extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @Column(unique = true, nullable = false)
  private String youtubeVideoId; // 유튜브 영상마다 있는 고유 ID 

  private Long length;

  private LocalDateTime uploadDate;

  @ManyToOne(fetch = FetchType.LAZY)
  private Channel channel;

  @OneToMany(mappedBy = "video", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<VideoItem> videoItems = new ArrayList<>();

  @OneToMany(mappedBy = "video", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<Timeframe> timeframes = new ArrayList<>();

  @Builder
  public Video(String title, String youtubeVideoId, Long length,
               LocalDateTime uploadDate, Channel channel) {
    this.title = title;
    this.youtubeVideoId = youtubeVideoId;
    this.length = length;
    this.uploadDate = uploadDate;
    this.channel = channel;
  }

  /* Function never used
  public void addTimeframes(Timeframe timeFrame) {
    this.timeframes.add(timeFrame);
  }
  */

  /* Function never used
  public void addVideoItems(VideoItem videoItem) {
    this.videoItems.add(videoItem);
  }
  */
}
