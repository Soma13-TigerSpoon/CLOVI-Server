package com.clovi.domain.ManyToMany;

import com.clovi.domain.Base.BaseTimeEntity;
import com.clovi.domain.TimeFrame;
import com.clovi.domain.item.Item;
import com.clovi.domain.item.ItemDetail;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeShopItem extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  private TimeFrame time;

  @ManyToOne(fetch = FetchType.LAZY)
  private Item item;

  @OneToOne(fetch = FetchType.LAZY)
  private ShopItem shopItem;

  @OneToOne(fetch = FetchType.LAZY)
  private ItemDetail itemDetail;
  public TimeShopItem(TimeFrame time, Item item, ShopItem shopItem, ItemDetail itemDetail) {
    this.time = time;
    this.item = item;
    this.shopItem = shopItem;
    this.itemDetail = itemDetail;
  }

}
