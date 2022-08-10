package Soma.CLOVI.dto.use;

import Soma.CLOVI.domain.TimeFrame;
import Soma.CLOVI.domain.youtube.Video;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class VideoResponseDto {
    private String creator;

    private String profileImgUrl;

    private List<TimeShopItemResponseDto> lists = new ArrayList<>();

    public VideoResponseDto(Video video){
        this.creator = video.getYoutubeCreator().getCreatorName();
        this.profileImgUrl = video.getYoutubeCreator().getProfileImgUrl();

        List<TimeFrame> timeFrames = video.getTimeFrames();
        System.out.println(timeFrames);
        if(!timeFrames.isEmpty()){
            for(TimeFrame timeFrame : video.getTimeFrames()){
                this.lists.add(new TimeShopItemResponseDto(timeFrame));
            }
        }

    }
}
