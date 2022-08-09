package Soma.CLOVI.domain.youtube;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Channel extends BaseTimeEntity {

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

}
