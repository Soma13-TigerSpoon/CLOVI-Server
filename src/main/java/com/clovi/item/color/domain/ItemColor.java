package com.clovi.item.color.domain;

import com.clovi.base.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.clovi.item.info.ItemInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemColor extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  private String imgUrl;

  @ManyToOne(fetch = FetchType.LAZY)
  private Color color;
  @ManyToOne(fetch = FetchType.LAZY)
  private ItemInfo itemInfo;

  public ItemColor(ItemInfo itemInfo, Color color, String imgUrl, Long memberId) {
    this.imgUrl = imgUrl;
    this.color = color;
    this.itemInfo = itemInfo;
    this.createBy = memberId;
    this.lastModifiedBy = memberId;
  }
}
