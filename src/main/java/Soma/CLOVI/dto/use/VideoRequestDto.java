package Soma.CLOVI.dto.use;

import lombok.Getter;

@Getter
public class VideoRequestDto {
    private String channelName;
    private String channelUrl;
    private String videoUrlId;
    private String videoTitle;
    private Long videoLength;
}
