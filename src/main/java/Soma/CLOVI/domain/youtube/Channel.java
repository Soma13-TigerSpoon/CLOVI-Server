package Soma.CLOVI.domain.youtube;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.user.YoutubeCreator;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Channel extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String channelUrl;
    private String profileImgUrl;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
    private List<Video> videos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private YoutubeCreator youtubeCreator;

    public Channel(String name, String url, String profileImgUrl, YoutubeCreator youtubeCreator) {
        this.name = name;
        this.channelUrl = "https://www.youtube.com"+url;
        this.profileImgUrl = profileImgUrl;
        this.youtubeCreator = youtubeCreator;
    }
    //지금까지 본 결과 channel url 이  "https://www.youtube.com" 뒤에 붙는 형식으로 두 가지가 있음.
    //1) c/채널이름
    //2) channel/고유 아이디
    public Channel(String name, String channelUrl, String profileImgUrl) {
        this.name = name;
        this.channelUrl = channelUrl;
        this.profileImgUrl = profileImgUrl;
    }

    public void addVideo(Video video){
        this.videos.add(video);
    }

}
