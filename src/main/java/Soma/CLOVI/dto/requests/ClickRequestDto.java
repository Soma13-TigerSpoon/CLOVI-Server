package Soma.CLOVI.dto.requests;

import lombok.Data;

@Data
public class ClickRequestDto {
    private Long videoId;
    private Long itemId;
    private Long shopId;
}
