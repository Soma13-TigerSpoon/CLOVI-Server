package com.clovi.app.config.filter;

import com.clovi.app.config.JwtTokenProvider;
import com.clovi.app.auth.service.MemberDetailService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

  private final JwtTokenProvider jwtTokenProvider;

  private final MemberDetailService memberDetailService;

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
      throws IOException, ServletException {
    String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);
    if (token != null && jwtTokenProvider.validateToken(token)) {   // token 검증
      String memberId = jwtTokenProvider.getUserPk(token);
      Authentication auth = memberDetailService.getAuthentication(memberId,token);    // 인증 객체 생성
      SecurityContextHolder.getContext().setAuthentication(auth); // SecurityContextHolder에 인증 객체 저장
    }
    chain.doFilter(servletRequest, servletResponse);
  }
}
