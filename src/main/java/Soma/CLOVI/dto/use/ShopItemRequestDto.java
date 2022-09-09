package Soma.CLOVI.dto.use;

import lombok.Getter;

@Getter
public class ShopItemRequestDto {

    private String shopName;
    private String name;
    private Long price;
    private String imgUrl;
    private String shopUrl;
}
