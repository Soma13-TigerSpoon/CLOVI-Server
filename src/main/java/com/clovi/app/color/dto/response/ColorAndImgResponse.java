package com.clovi.app.color.dto.response;

import com.clovi.app.color.domain.ItemColor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ColorAndImgResponse {

    @Schema(description = "상품 이미지 링크", example = "https://image.msscdn.net/images/goods_img/20221204/2970721/2970721_1_500.jpg")
    private String imgUrl;

    @Schema(description = "상품 색", example = "black")
    private String color;

    @Builder
    public ColorAndImgResponse(String color, String imgUrl) {
        this.color = color;
        this.imgUrl = imgUrl;
    }

    public static ColorAndImgResponse from(ItemColor itemColor) {
        return ColorAndImgResponse.builder()
                .color(itemColor.getColor().getName())
                .imgUrl(itemColor.getImgUrl())
                .build();
    }
}
