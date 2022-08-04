package Soma.CLOVI.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ShopItemDto {

    @NotNull
    private String videoUrl;

    @NotNull
    private Long timeId;

    @NotNull
    private String itemUrl;

    //crawling
    @NotNull
    private String itemImgUrl;

    //crawling
    @NotNull
    private Long price;

    private Long stock;

    //Item
    //crawling
    @NotNull
    private String itemName;

    private String description;

    @NotNull
    private String color;

    @NotNull
    private String size;

    //crawling
    @NotNull
    private String itemType;

    //shop
    //crawling
    @NotNull
    private String shopName;

}
