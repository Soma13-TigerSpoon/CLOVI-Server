package Soma.CLOVI.domain.user;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.youtube.Channel;
import Soma.CLOVI.domain.youtube.Video;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class YoutubeCreator extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String creatorName;
    private String profileImgUrl;

    @OneToMany(mappedBy = "youtubeCreator", cascade = CascadeType.ALL)
    private List<Channel> channels = new ArrayList<>();

    public YoutubeCreator(String creatorName, String profileImgUrl) {
        this.creatorName = creatorName;
        this.profileImgUrl = profileImgUrl;
    }

    public void addChannel(Channel channel){
        this.channels.add(channel);
    }


    /*
    @OneToMany(mappedBy = "creator")
    private List<Channel> channel = new ArrayList<>();*/
}
