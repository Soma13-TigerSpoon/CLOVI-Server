package com.clovi.domain.ManyToMany;

import com.clovi.domain.Base.BaseTimeEntity;
import com.clovi.domain.item.Item;
import com.clovi.domain.youtube.Video;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VideoItem extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Video video;

  @ManyToOne(fetch = FetchType.LAZY)
  private Item item;

  public VideoItem(Video video, Item item) {
    this.video = video;
    this.item = item;
  }


}
