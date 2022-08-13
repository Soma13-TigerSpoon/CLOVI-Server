package Soma.CLOVI.domain;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.domain.youtube.Video;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = {
        @Index(name = "i_start_time", columnList = "startTime"),
        @Index(name = "i_end_time", columnList = "endTime")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeFrame extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_id")
    private Long id;

    private Long startTime;
    private Long endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Video video;


    @OneToMany(mappedBy = "timeFrame", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private Model model;


    @Builder
    public TimeFrame(Long startTime, Long endTime, Model model, Video video){
        this.startTime = startTime;
        this.endTime = endTime;
        this.model = model;
        this.video = video;
    }

    public void addItem(Item item){
        this.items.add(item);
    }

}
