package Soma.CLOVI.domain.shop;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.ManyToMany.ShopItem;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(indexes = {
    @Index(name = "i_shop_name", columnList = "name")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shop extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String description;

  private String url;

  private String logoUrl;

  @OneToMany(mappedBy = "shop")
  private List<ShopItem> shopItems = new ArrayList<>();

  public Shop(String name, String logoUrl) {
    this.name = name;
    this.logoUrl = logoUrl;
  }

  public void addShopItem(ShopItem shopItem) {
    this.shopItems.add(shopItem);
  }
}
