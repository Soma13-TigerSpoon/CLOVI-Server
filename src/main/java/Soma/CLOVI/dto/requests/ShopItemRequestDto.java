package Soma.CLOVI.dto.requests;

import lombok.Data;
import lombok.Getter;

@Data
public class ShopItemRequestDto {
  private Long shopId;
  private Long itemId;

  private String shopName;
  private String shopUrl;
  private String imgUrl;
  private String name;

  private Long price;
}
