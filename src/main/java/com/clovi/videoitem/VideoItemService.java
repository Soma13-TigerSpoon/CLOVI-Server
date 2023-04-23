package com.clovi.videoitem;
import com.clovi.video.Video;
import com.clovi.videoitem.dto.response.VideoItemResponseDto;
import com.clovi.timeframe.repository.TimeFrameRepositoryCustomImpl;
import com.clovi.video.repository.VideoRepositoryCustomImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VideoItemService {
    private final TimeFrameRepositoryCustomImpl timeFrameRepositoryCustomImpl;
    private final VideoRepositoryCustomImpl videoRepositoryCustomImpl;
    public List<VideoItemResponseDto> getVideosByItemId(Long itemId) {
        List<Video> videoList = videoRepositoryCustomImpl.filterByItemId(itemId);
        return videoList
                .stream()
                .map((video) -> new VideoItemResponseDto(video, timeFrameRepositoryCustomImpl.getTimeFramesByVideoIdAndItemInfoId(video.getId(),itemId)))
                .collect(Collectors.toList());
    }
}