package Soma.CLOVI.dto.response;

import Soma.CLOVI.dto.requests.ClickRequestDto;
import lombok.Getter;

@Getter
public class ClickResponseDto {
    private String clickDateTime;
    private String ipAddress;
    private Long videoId;
    private Long itemId;
    private Long shopId;

    public ClickResponseDto(String clickDateTime, String ipAddress, ClickRequestDto clickRequestDto) {
        this.clickDateTime = clickDateTime;
        this.ipAddress = ipAddress;
        this.videoId = clickRequestDto.getVideoId();
        this.itemId = clickRequestDto.getItemId();
        this.shopId = clickRequestDto.getShopId();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append(clickDateTime).append("\t");
        sb.append(ipAddress).append("\t");
        sb.append(videoId).append("\t");
        sb.append(itemId).append("\t");
        sb.append(shopId);

        return sb.toString();
    }
}
