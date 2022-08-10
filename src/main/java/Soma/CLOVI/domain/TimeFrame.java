package Soma.CLOVI.domain;

import Soma.CLOVI.domain.Base.BaseEntity;
import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.domain.youtube.Video;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = {
        @Index(name = "i_start_time", columnList = "startTime"),
        @Index(name = "i_end_time", columnList = "endTime")
})
@Getter
public class TimeFrame extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "time_id")
    private Long id;

    private Long startTime;
    private Long endTime;

    @Column(name = "video_id")
    private Long videoId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "time_id")
    private List<ShopItem> itemList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private Model model;


    @Builder
    public TimeFrame(Long startTime, Long endTime, Model model, List<ShopItem> shopItems){
        this.startTime = startTime;
        this.endTime = endTime;
        this.model = model;
        for(ShopItem shopItem : shopItems){
            this.itemList.add(shopItem);
        }
    }

    public TimeFrame() {

    }
}
