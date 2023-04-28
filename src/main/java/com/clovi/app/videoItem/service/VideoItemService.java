package com.clovi.app.videoItem.service;
import com.clovi.app.timeframe.repository.TimeframeRepositoryCustomImpl;
import com.clovi.app.video.domain.Video;
import com.clovi.app.video.repository.VideoRepositoryCustomImpl;
import com.clovi.app.videoItem.dto.response.VideoItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VideoItemService {
    private final TimeframeRepositoryCustomImpl timeFrameRepositoryCustomImpl;
    private final VideoRepositoryCustomImpl videoRepositoryCustomImpl;
    public List<VideoItemResponse> getVideosByItemId(Long itemId) {
        List<Video> videoList = videoRepositoryCustomImpl.filterByItemId(itemId);
        return videoList
                .stream()
                .map((video) -> new VideoItemResponse(video, timeFrameRepositoryCustomImpl.getTimeFramesByVideoIdAndItemInfoId(video.getId(),itemId)))
                .collect(Collectors.toList());
    }
}