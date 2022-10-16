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

  public ShopItemRequestDto(String shopUrl, Long price) {
    this.shopUrl = shopUrl;
    this.price = price;
  }
  // 빈 생성자 없으면 error 발생
  public ShopItemRequestDto() {
  }
}
