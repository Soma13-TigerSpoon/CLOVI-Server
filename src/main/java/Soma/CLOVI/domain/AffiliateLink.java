package Soma.CLOVI.domain;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.ManyToMany.ShopItem;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 유튜브 제휴 링크 테이블
 */

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AffiliateLink extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @OneToOne
  @JoinColumn(name = "shop_item_id")
  private ShopItem shopItem;


  @Column(updatable = false)
  private LocalDateTime validDate = LocalDateTime.now().plusDays(30);

  public void setShopItem(ShopItem shopItem) {
    this.shopItem = shopItem;
  }
}
