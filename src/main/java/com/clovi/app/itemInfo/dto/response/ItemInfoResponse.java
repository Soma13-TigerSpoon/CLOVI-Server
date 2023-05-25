package com.clovi.app.itemInfo.dto.response;

import com.clovi.app.color.dto.response.ColorAndImgResponse;
import com.clovi.app.itemInfo.ItemInfo;
import com.clovi.app.shopItem.dto.response.ShopItemResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ItemInfoResponse {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "브랜드", example = "어누즈")
    private String brand;

    @Schema(description = "상품명", example = "엠보 브이넥 니트")
    private String name;

    @Schema(description = "카테고리", example = "203")
    private Long categoryId;

    @Schema(description = "쇼핑몰 객체")
    private List<ShopItemResponse> shopItems = new ArrayList<>();

    @Schema(description = "색상, 이미지 주소 리스트 ")
    private List<ColorAndImgResponse> colorAndImgUrlList = new ArrayList<>();

    @Schema(description = "사이즈 리스트")
    private List<String> sizeList = new ArrayList<>();

    @Builder
    public ItemInfoResponse(Long id, String brand, String name, Long categoryId,List<ColorAndImgResponse> colorAndImgUrlList, List<String> sizeList, List<ShopItemResponse> shopItems) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.categoryId = categoryId;
        this.colorAndImgUrlList = colorAndImgUrlList;
        this.sizeList = sizeList;
        this.shopItems = shopItems;
    }

    public static ItemInfoResponse from(final ItemInfo itemInfo){
        return ItemInfoResponse.builder()
                .id(itemInfo.getId())
                .name(itemInfo.getName())
                .brand(itemInfo.getBrand())
                .categoryId(itemInfo.getCategory().getId())
                .colorAndImgUrlList(itemInfo.getItemColors().stream().map(itemColor -> ColorAndImgResponse.from(itemColor)).collect(Collectors.toList()))
                .sizeList(itemInfo.getItemSizes().stream().map(itemSize -> itemSize.getSize().getName()).collect(Collectors.toList()))
                .shopItems(itemInfo.getShopItems().stream()
                        .filter(shopItem -> shopItem.isNotDeleted()) // 삭제되지 않은 것들만
                        .map(shopItem -> ShopItemResponse.from(shopItem)).collect(Collectors.toList()))
                .build();
    }
}
