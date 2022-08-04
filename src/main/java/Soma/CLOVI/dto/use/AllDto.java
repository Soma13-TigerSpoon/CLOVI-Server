package Soma.CLOVI.dto.use;

import Soma.CLOVI.domain.Model;
import Soma.CLOVI.domain.TimeFrame;
import Soma.CLOVI.domain.youtube.Video;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AllDto {


    private String creatorName;

    private String profileImgUrl;

    private List<TimeShopItemDto> timeShopItemDtoList = new ArrayList<>();

    public AllDto(Video video){
        this.creatorName = video.getYoutubeCreator().getCreatorName();
        this.profileImgUrl = video.getYoutubeCreator().getProfileImgUrl();

        List<TimeFrame> timeFrames = video.getTimeFrames();
        System.out.println(timeFrames);
        if(!timeFrames.isEmpty()){
            for(TimeFrame timeFrame : video.getTimeFrames()){
                this.timeShopItemDtoList.add(new TimeShopItemDto(timeFrame));
            }
        }

    }
}
