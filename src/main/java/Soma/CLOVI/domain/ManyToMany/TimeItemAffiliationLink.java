package Soma.CLOVI.domain.ManyToMany;

import Soma.CLOVI.domain.AffiliateLink;
import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.TimeFrame;
import Soma.CLOVI.domain.item.Item;
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
import org.hibernate.annotations.BatchSize;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeItemAffiliationLink extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  private TimeFrame time;

  @ManyToOne(fetch = FetchType.LAZY)
  private Item item;

  @OneToOne(fetch = FetchType.LAZY)
  //@BatchSize(size = 100) // 이걸 나두면
  private AffiliateLink affiliateLink;

  public TimeItemAffiliationLink(TimeFrame time, Item item, AffiliateLink affiliateLink) {
    this.time = time;
    this.item = item;
    this.affiliateLink = affiliateLink;
  }

}
