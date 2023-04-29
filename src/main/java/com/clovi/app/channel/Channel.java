package com.clovi.app.channel;

import com.clovi.app.base.domain.BaseTimeEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.clovi.app.video.Video;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Channel extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String channelId;
  private String profileImgUrl;

  @OneToMany(mappedBy = "channel", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<Video> videos = new ArrayList<>();

  //지금까지 본 결과 channel url 이  "https://www.youtube.com" 뒤에 붙는 형식으로 두 가지가 있음.
  //1) c/채널이름
  //2) channel/고유 아이디
  public Channel(String name, String channelId, String profileImgUrl) {
    this.name = name;
    this.channelId = channelId;
    this.profileImgUrl = profileImgUrl;
  }

  public void addVideo(Video video) {
    this.videos.add(video);
  }

}
