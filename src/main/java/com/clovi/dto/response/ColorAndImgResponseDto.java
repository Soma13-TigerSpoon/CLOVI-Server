package com.clovi.dto.response;

import com.clovi.domain.item.ItemColor;
import lombok.Getter;

@Getter
public class ColorAndImgResponseDto {

    private String color;

    private String imgUrl;

    public ColorAndImgResponseDto(String color, String imgUrl) {
        this.color = color;
        this.imgUrl = imgUrl;
    }
    public ColorAndImgResponseDto(ItemColor itemColor) {
        this.color = itemColor.getColor().getName();
        this.imgUrl = itemColor.getImgUrl();
    }
}
