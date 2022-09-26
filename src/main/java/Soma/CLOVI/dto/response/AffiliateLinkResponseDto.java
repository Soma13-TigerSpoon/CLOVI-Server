package Soma.CLOVI.dto.response;

import Soma.CLOVI.domain.AffiliateLink;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class AffiliateLinkResponseDto {
  private Long id;
  private String url;
  private Long price;


  public AffiliateLinkResponseDto(AffiliateLink affiliateLink) {
    if(affiliateLink.getValidDate().isAfter(LocalDateTime.now())){ // 유효한 기간이 남았을 경우
      this.id = affiliateLink.getId();
      this.url = affiliateLink.getUrl();
      this.price = affiliateLink.getPrice();
    }
  }
}
