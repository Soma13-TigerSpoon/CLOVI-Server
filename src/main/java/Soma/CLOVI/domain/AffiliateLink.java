package Soma.CLOVI.domain;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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

  @Lob
  private String url;

  private Long price;
  @Column(updatable = false)
  private LocalDateTime validDate = LocalDateTime.now().plusDays(30);

  public AffiliateLink(String url, Long price) {
    this.url = url;
    this.price = price;
  }

}
