package Soma.CLOVI.domain.ManyToMany;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.domain.shop.Shop;
import Soma.CLOVI.domain.youtube.Video;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VideoItem extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Video video;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Item item;

    public VideoItem(Video video, Item item) {
        this.video = video;
        this.item = item;
    }


}
