package Soma.CLOVI.dto.requests;

import lombok.Data;
import lombok.Getter;

@Data
public class ShopItemRequestDto {
  private Long itemId;

  private String hostname;
  private String shopUrl;
  private String imgUrl;
  private String name;

  private Long price;
}
