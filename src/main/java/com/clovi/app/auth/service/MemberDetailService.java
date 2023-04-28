package com.clovi.app.auth.service;

import com.clovi.app.exception.ResourceNotFoundException;
import com.clovi.app.member.Member;
import com.clovi.app.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {

  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Member member = memberRepository.findById(Long.valueOf(username)).orElseThrow(() -> new ResourceNotFoundException("회원",username));
    return new PrincipalDetails(member);
  }

  // 인증 성공시 SecurityContextHolder에 저장할 Authentication 객체 생성
  public Authentication getAuthentication(String memberId, String token) {
    UserDetails userDetails = loadUserByUsername(memberId);
    return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
  }
}
