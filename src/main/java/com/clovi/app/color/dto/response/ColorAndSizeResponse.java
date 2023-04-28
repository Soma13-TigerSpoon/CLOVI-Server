package com.clovi.app.color.dto.response;

import com.clovi.app.item.domain.Item;
import lombok.Getter;

@Getter
public class ColorAndSizeResponse {
    private String color;
    private String size;

    public ColorAndSizeResponse(String color, String size) {
        this.color = color;
        this.size = size;
    }
    public ColorAndSizeResponse(Item item) {
        this.color = item.getColor();
        this.size = item.getSize();
    }
}
