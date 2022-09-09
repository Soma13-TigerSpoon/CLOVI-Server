package Soma.CLOVI.dto.use;

import lombok.Getter;

import static Soma.CLOVI.common.common.StringTimeToLong;

@Getter
public class VideoRequestDto {
    private String channelName;
    private String channelImg;
    private String channelUrl;
    private String videoUrlId;
    private String videoTitle;
    private String videoLength;
    public Long getVideoLength(){
        return StringTimeToLong(videoLength);
    }
}
