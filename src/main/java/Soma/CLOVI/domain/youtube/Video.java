package Soma.CLOVI.domain.youtube;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.ManyToMany.VideoItem;
import Soma.CLOVI.domain.TimeFrame;
import Soma.CLOVI.domain.user.YoutubeCreator;
import Soma.CLOVI.dto.use.VideoRequestDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = {
        @Index(name = "i_title", columnList = "title"),
        @Index(name = "i_video_url", columnList = "videoUrl")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Video extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(unique = true, nullable = false)
    private String videoUrl;

    private String thumbnailUrl;

    private Long length;


    @ManyToOne(fetch = FetchType.LAZY)
    private Channel channel;

    @ManyToOne(fetch = FetchType.LAZY)
    private YoutubeCreator youtubeCreator;

    @OneToMany(mappedBy = "video", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<VideoItem> videoItems = new ArrayList<>();

    @OneToMany(mappedBy = "video", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<TimeFrame> timeFrames = new ArrayList<>();

    public Video(String title, String videoUrl, Long length, YoutubeCreator youtubeCreator, Channel channel) {
        this.title = title;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = String.format("https://img.youtube.com/vi/%s/default.jpg",this.videoUrl);;
        this.length = length;
        this.youtubeCreator = youtubeCreator;
        this.channel = channel;
    }
    public Video(VideoRequestDto videoRequestDto, Channel channel) {
        this.title = videoRequestDto.getVideoTitle();
        this.videoUrl = videoRequestDto.getVideoUrlId();
        this.thumbnailUrl = String.format("https://img.youtube.com/vi/%s/default.jpg",this.videoUrl);
        this.length = videoRequestDto.getVideoLength();
        this.youtubeCreator = channel.getYoutubeCreator();
        this.channel = channel;
    }
    public void addTimeFrame(TimeFrame timeFrame){
        this.timeFrames.add(timeFrame);
    }
    public void addVideoItem(VideoItem videoItem) {
        this.videoItems.add(videoItem);
    }

}
