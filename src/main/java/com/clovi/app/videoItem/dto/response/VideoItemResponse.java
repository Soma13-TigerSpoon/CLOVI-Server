package com.clovi.app.videoItem.dto.response;

import com.clovi.app.timeframe.Timeframe;
import com.clovi.app.timeframe.dto.response.TimeframeModelResponse;
import com.clovi.app.video.Video;
import lombok.Getter;

import java.util.List;
import java.util.ArrayList;

@Getter
public class VideoItemResponse {
    private final Long videoId;
    private final String title;
    private final String creator;
    private final String profileImgUrl;
    private final String videoUrl;
    private final List<TimeframeModelResponse> timeModelList = new ArrayList<>();

    public VideoItemResponse(Video video, List<Timeframe> times) {
        this.videoId = video.getId();
        this.title = video.getTitle();
        this.creator = video.getChannel().getName();
        this.profileImgUrl = video.getChannel().getProfileImgUrl();
        this.videoUrl = video.getYoutubeVideoId();
        for(Timeframe timeFrame : times){
            this.timeModelList.add(new TimeframeModelResponse(timeFrame));
        }
    }

}
