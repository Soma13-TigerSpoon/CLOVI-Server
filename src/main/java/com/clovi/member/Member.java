package com.clovi.member;

import com.clovi.base.domain.BaseTimeEntity;
import com.clovi.channel.Channel;
import com.clovi.auth.domain.ProviderName;
import com.clovi.auth.domain.RoleName;
import com.clovi.member.dto.request.MemberCreateRequest;

import javax.persistence.*;

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

  @ManyToOne(fetch = FetchType.LAZY)
  private Channel channel;

  @Enumerated(EnumType.STRING)
  private ProviderName providerName;

  @Enumerated(EnumType.STRING)
  private RoleName role;
  public Member(MemberCreateRequest memberCreateRequest, Channel channel) {
    this.memberId = memberCreateRequest.getMemberId();
    this.providerName = ProviderName.CLOVI;
    this.role = RoleName.Y_CREATOR_USER;
    this.channel = channel;
  }

  public void setEncodedPassword(String encodedPassword){
    this.password = encodedPassword;
  }

}
