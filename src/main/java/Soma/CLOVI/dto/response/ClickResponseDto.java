package Soma.CLOVI.dto.response;

import Soma.CLOVI.dto.requests.ClickRequestDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ClickResponseDto {
    private LocalDateTime clickDateTime;
    private String ipAddress;
    private Long videoId;
    private Long itemId;
    private Long shopId;

    public ClickResponseDto(LocalDateTime clickDateTime, String ipAddress, ClickRequestDto clickRequestDto) {
        this.clickDateTime = clickDateTime;
        this.ipAddress = ipAddress;
        this.videoId = clickRequestDto.getVideoId();
        this.itemId = clickRequestDto.getItemId();
        this.shopId = clickRequestDto.getShopId();
    }
}
