package Soma.CLOVI.domain.youtube;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.TimeFrame;
import Soma.CLOVI.domain.user.YoutubeCreator;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = {
        @Index(name = "i_title", columnList = "title"),
        @Index(name = "i_video_url", columnList = "videoUrl")
})
@Getter @Setter
public class Video extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "video_id")
    private Long id;

    private String title;

    @Column(unique = true, nullable = false)
    private String videoUrl;

    private String thumbnailUrl;

    private Long length;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private YoutubeCreator youtubeCreator;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "video_id")
    private List<TimeFrame> timeFrames = new ArrayList<>();

    @Builder
    public Video(String title, String videoUrl, String thumbnailUrl, Long length, YoutubeCreator youtubeCreator, Channel channel, List<TimeFrame> timeFrames) {
        this.title = title;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.length = length;
        this.youtubeCreator = youtubeCreator;
        this.channel = channel;
        for(TimeFrame timeFrame : timeFrames){
            this.timeFrames.add(timeFrame);
        }
    }

    public Video() {

    }
}
