package com.clovi.app.auth.domain;

import com.clovi.app.base.domain.BaseTimeEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String refreshToken;

  private Long memberId;

  public RefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
  public RefreshToken(String refreshToken, Long memberId) {
    this.refreshToken = refreshToken;
    this.memberId = memberId;
  }
  public void update(String token){
    this.refreshToken = token;
  }

}