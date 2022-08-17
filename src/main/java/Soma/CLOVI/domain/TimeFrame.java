package Soma.CLOVI.domain;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.ManyToMany.TimeItem;
import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.domain.youtube.Video;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = {
        @Index(name = "i_start_time", columnList = "start"),
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeFrame extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_id")
    private Long id;

    private Long start;
    private Long end;

    @ManyToOne(fetch = FetchType.LAZY)
    private Video video;


    @OneToMany(mappedBy = "time", cascade = CascadeType.ALL)
    private List<TimeItem> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private Model model;


    @Builder
    public TimeFrame(Long start, Long end, Model model, Video video){
        this.start = start;
        this.end = end;
        this.model = model;
        this.video = video;
    }

    public void addItem(TimeItem timeItem){
        this.items.add(timeItem);
    }

}
