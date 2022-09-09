package Soma.CLOVI.domain;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.ManyToMany.TimeItem;
import Soma.CLOVI.domain.youtube.Video;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = {
        @Index(name = "i_start_time", columnList = "capturePoint"),
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeFrame extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_id")
    private Long id;

    private Long capturePoint;

    @ManyToOne(fetch = FetchType.LAZY)
    private Video video;


    @OneToMany(mappedBy = "time", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<TimeItem> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private Model model;


    @Builder
    public TimeFrame(Long capturePoint, Model model, Video video){
        this.capturePoint = capturePoint;
        this.model = model;
        this.video = video;
    }

    public void addItem(TimeItem timeItem){
        this.items.add(timeItem);
    }

}
