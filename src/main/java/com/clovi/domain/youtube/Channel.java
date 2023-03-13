package com.clovi.domain.youtube;

import com.clovi.domain.Base.BaseTimeEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

  private String channelUrl;
  private String profileImgUrl;

  @OneToMany(mappedBy = "channel", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<Video> videos = new ArrayList<>();

  //지금까지 본 결과 channel url 이  "https://www.youtube.com" 뒤에 붙는 형식으로 두 가지가 있음.
  //1) c/채널이름
  //2) channel/고유 아이디
  public Channel(String name, String channelUrl, String profileImgUrl) {
    this.name = name;
    this.channelUrl = channelUrl;
    this.profileImgUrl = profileImgUrl;
  }

  public void addVideo(Video video) {
    this.videos.add(video);
  }

}
