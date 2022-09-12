package Soma.CLOVI.domain.ManyToMany;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.TimeFrame;
import Soma.CLOVI.domain.item.Item;
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
public class TimeItem extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  private TimeFrame time;

  @ManyToOne(fetch = FetchType.LAZY)
  private Item item;


  public TimeItem(TimeFrame time, Item item) {
    this.time = time;
    this.item = item;
  }

}
