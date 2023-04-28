package com.clovi.app.color.dto.response;

import com.clovi.app.color.domain.ItemColor;
import lombok.Getter;

@Getter
public class ColorAndImgResponse {

    private String color;

    private String imgUrl;

    public ColorAndImgResponse(String color, String imgUrl) {
        this.color = color;
        this.imgUrl = imgUrl;
    }
    public ColorAndImgResponse(ItemColor itemColor) {
        this.color = itemColor.getColor().getName();
        this.imgUrl = itemColor.getImgUrl();
    }
}
