package Soma.CLOVI.domain.youtube;

import Soma.CLOVI.domain.user.YoutubeCreator;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Channel {

    @Id @GeneratedValue
    @Column(name = "channel_id")
    private Long id;

    private String Name;
    private String Url;
    private String profileImgUrl;
    @Builder
    public Channel(String name, String url, String profileImgUrl) {
        Name = name;
        Url = url;
        this.profileImgUrl = profileImgUrl;
    }

    public Channel() {

    }
}
