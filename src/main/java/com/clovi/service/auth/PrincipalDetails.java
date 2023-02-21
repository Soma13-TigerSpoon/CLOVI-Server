package com.clovi.service.auth;

import com.clovi.domain.user.Member;
import com.clovi.repository.MemberRepository;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
public class PrincipalDetails implements UserDetails {
  @Autowired
  private MemberRepository memberRepository;

  private Member member;

  //일반 시큐리티 로그인시 사용
  public PrincipalDetails(Member member){
    this.member = member;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Arrays.asList(new SimpleGrantedAuthority(member.getRole().getName()));
  }

  @Override
  public String getPassword() {
    return member.getPassword();
  }

  @Override
  public String getUsername() {
    return member.getMemberId();
  }

  @Override
  public boolean isAccountNonExpired() {
    return member.deleted() == false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
