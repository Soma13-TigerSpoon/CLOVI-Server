package com.clovi.item.color.dto.response;

import com.clovi.item.color.domain.ItemColor;
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
