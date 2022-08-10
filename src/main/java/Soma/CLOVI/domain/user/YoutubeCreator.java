package Soma.CLOVI.domain.user;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
public class YoutubeCreator extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name="creator_id")
    private Long id;

    private String creatorName;
    private String profileImgUrl;

    public YoutubeCreator() {

    }
    @Builder
    public YoutubeCreator(String creatorName, String profileImgUrl) {
        this.creatorName = creatorName;
        this.profileImgUrl = profileImgUrl;
    }

    /*
    @OneToMany(mappedBy = "creator")
    private List<Channel> channel = new ArrayList<>();*/
}
