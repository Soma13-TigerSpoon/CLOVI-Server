package Soma.CLOVI.domain.user;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.youtube.Channel;
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
public class YoutubeCreator extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String creatorName;
  private String profileImgUrl;

  @OneToMany(mappedBy = "youtubeCreator", cascade = CascadeType.ALL)
  private List<Channel> channels = new ArrayList<>();

  public YoutubeCreator(String creatorName) {
    this.creatorName = creatorName;
  }

  public void addChannel(Channel channel) {
    this.channels.add(channel);
  }


    /*
    @OneToMany(mappedBy = "creator")
    private List<Channel> channel = new ArrayList<>();*/
}
