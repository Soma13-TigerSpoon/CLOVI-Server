package com.clovi.app.timeShopItem;

import com.clovi.app.item.Item;
import com.clovi.app.base.domain.BaseEntity;
import com.clovi.app.timeframe.Timeframe;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.clovi.app.shopItem.ShopItem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeShopItem extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  private Timeframe time;
  @ManyToOne(fetch = FetchType.LAZY)
  private Item item;
  @OneToOne(fetch = FetchType.LAZY)
  private ShopItem shopItem;

  public TimeShopItem(Timeframe time, Item item, ShopItem shopItem, Long memberId) {
    this.time = time;
    this.item = item;
    this.shopItem = shopItem;
    this.createBy = memberId;
    this.lastModifiedBy = memberId;
  }

}
