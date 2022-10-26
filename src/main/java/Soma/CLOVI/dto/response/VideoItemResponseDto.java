package Soma.CLOVI.dto.response;

import Soma.CLOVI.domain.youtube.Video;
import lombok.Getter;

@Getter
public class VideoItemResponseDto {
    private final VideoResponseDto video;
    private final TimeResponseDto time;

    public VideoItemResponseDto(Video video) {
        this.video = new VideoResponseDto(video);
        this.time = null;
    }
}
