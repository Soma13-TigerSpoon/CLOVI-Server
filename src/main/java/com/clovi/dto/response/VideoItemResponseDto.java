package com.clovi.dto.response;

import com.clovi.domain.ManyToMany.TimeShopItem;
import com.clovi.domain.TimeFrame;
import com.clovi.domain.item.ItemInfo;
import com.clovi.domain.youtube.Video;
import lombok.Getter;

import java.util.List;
import java.util.ArrayList;

@Getter
public class VideoItemResponseDto {
    private final Long videoId;
    private final String title;
    private final String creator;
    private final String profileImgUrl;
    private final String videoUrl;
    private final List<TimeModelResponseDto> timeModelList = new ArrayList<>();

    public VideoItemResponseDto(Video video, List<TimeFrame> times) {
        this.videoId = video.getId();
        this.title = video.getTitle();
        this.creator = video.getChannel().getName();
        this.profileImgUrl = video.getChannel().getProfileImgUrl();
        this.videoUrl = video.getYoutubeVideoId();
        for(TimeFrame timeFrame : times){
            this.timeModelList.add(new TimeModelResponseDto(timeFrame));
        }
    }

}
