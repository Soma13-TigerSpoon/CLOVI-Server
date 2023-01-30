package Soma.CLOVI.dto.response;

import Soma.CLOVI.domain.ManyToMany.TimeShopItem;
import Soma.CLOVI.domain.TimeFrame;
import Soma.CLOVI.domain.youtube.Video;
import lombok.Getter;

import java.util.List;
import java.util.ArrayList;

@Getter
public class VideoItemResponseDto {
    private final Long itemId;
    private final Long videoId;
    private final String title;
    private final String creator;
    private final String profileImgUrl;
    private final String videoUrl;
    private final List<TimeModelResponseDto> timeModelList = new ArrayList<>();

    public VideoItemResponseDto(Video video, Long itemId) {
        this.itemId = itemId;
        this.videoId = video.getId();
        this.title = video.getTitle();
        this.creator = video.getChannel().getName();
        this.profileImgUrl = video.getChannel().getProfileImgUrl();
        this.videoUrl = video.getYoutubeVideoId();

        List<TimeFrame> timeFrames = video.getTimeFrames();
        for(TimeFrame timeFrame : timeFrames) {
            if(checkItemExists(timeFrame, itemId)) {
                this.timeModelList.add(new TimeModelResponseDto(timeFrame));
            }
        }
    }

    private boolean checkItemExists(TimeFrame timeFrame, Long itemId) {
        List<TimeShopItem> items = timeFrame.getItems();
        for(TimeShopItem item : items) {
            if(item.getItem().getId().equals(itemId)) {
                return true;
            }
        }

        return false;
    }
}
