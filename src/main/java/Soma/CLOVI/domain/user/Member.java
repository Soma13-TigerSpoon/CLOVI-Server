package Soma.CLOVI.domain.user;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.dto.requests.member.MemberCreateRequest;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String memberId;

  private String password;

  @Enumerated(EnumType.STRING)
  private ProviderName providerName;

  @Enumerated(EnumType.STRING)
  private RoleName role;
  public Member(MemberCreateRequest memberCreateRequest) {
    this.memberId = memberCreateRequest.getMemberId();
    this.providerName = ProviderName.CLOVI;
    this.role = RoleName.Y_CREATOR;
  }

  public void setEncodedPassword(String encodedPassword){
    this.password = encodedPassword;
  }
}
