package Soma.CLOVI.service;

import Soma.CLOVI.domain.youtube.Video;
import Soma.CLOVI.dto.response.VideoItemResponseDto;
import Soma.CLOVI.repository.Video.VideoRepositoryCustomImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VideoItemService {
    private final VideoRepositoryCustomImpl videoRepositoryCustomImpl;

    public List<VideoItemResponseDto> getVideosByItemId(Long itemId) {
        List<Video> videoList = videoRepositoryCustomImpl.filterByItemId(itemId);

        return videoList
                .stream()
                .map((video) -> new VideoItemResponseDto(video, itemId))
                .collect(Collectors.toList());
    }
}