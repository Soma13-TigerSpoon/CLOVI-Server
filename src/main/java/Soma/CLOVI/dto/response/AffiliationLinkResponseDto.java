package Soma.CLOVI.dto.response;

import Soma.CLOVI.domain.AffiliationLink;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class AffiliationLinkResponseDto {
  private Long id;
  private String url;
  private Long price;


  public AffiliationLinkResponseDto(AffiliationLink affiliationLink) {
    if(affiliationLink.getValidDate().isAfter(LocalDateTime.now())){ // 유효한 기간이 남았을 경우
      this.id = affiliationLink.getId();
      this.url = affiliationLink.getUrl();
      this.price = affiliationLink.getPrice();
    }
  }
}
