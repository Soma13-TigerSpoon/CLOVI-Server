package Soma.CLOVI.domain.user;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class YoutubeCreator extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="creator_id")
    private Long id;

    private String creatorName;
    private String profileImgUrl;
    @Builder
    public YoutubeCreator(String creatorName, String profileImgUrl) {
        this.creatorName = creatorName;
        this.profileImgUrl = profileImgUrl;
    }

    /*
    @OneToMany(mappedBy = "creator")
    private List<Channel> channel = new ArrayList<>();*/
}
