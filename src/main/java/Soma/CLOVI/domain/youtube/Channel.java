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

    @Id @GeneratedValue
    private Long id;

    private String Name;
    private String Url;
    private String profileImgUrl;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
    private List<Video> videos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private YoutubeCreator youtubeCreator;

    public Channel(String name, String url, String profileImgUrl, YoutubeCreator youtubeCreator) {
        this.Name = name;
        this.Url = url;
        this.profileImgUrl = profileImgUrl;
        this.youtubeCreator = youtubeCreator;
    }
    public void addVideo(Video video){
        this.videos.add(video);
    }

}
