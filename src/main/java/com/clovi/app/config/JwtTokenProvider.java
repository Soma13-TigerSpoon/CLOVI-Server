package com.clovi.app.config;

import com.clovi.app.exception.auth.TokenNotValidException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class JwtTokenProvider {
  public static final String AUTHORIZATION_HEADER = "Authorization";
  private Key key;
  private final long accessTokenValidityInMilliSeconds; // 엑세스 토큰 만료시간
  private final long refreshTokenValidityInMilliSeconds; // 리프레시 토큰 만료시간

  public JwtTokenProvider(@Value("${jwt.token.secret-key}") String secretKey,
      @Value("${jwt.token.access.expire-time}") long accessTokenValidityInSeconds,
      @Value("${jwt.token.refresh.expire-time}") long refreshTokenValidityInSeconds ) {
    this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    this.accessTokenValidityInMilliSeconds = accessTokenValidityInSeconds * 1000;
    this.refreshTokenValidityInMilliSeconds = refreshTokenValidityInSeconds * 1000;

  }

  public String createAccessToken(Long id){
    return createToken(id,accessTokenValidityInMilliSeconds);
  }

  public String createRefreshToken(Long id){
    return createToken(id,refreshTokenValidityInMilliSeconds);
  }
  public String createToken(Long id, long validityInMilliseconds) {
    final String payload = String.valueOf(id);

    Date now = new Date();
    Date validity = new Date(now.getTime() + validityInMilliseconds);

    return Jwts.builder()
        .setSubject(payload)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();

  }

  public String getPayload(String token) {
    return parseClaims(token)
        .getBody()
        .getSubject();
  }

  private Jws<Claims> parseClaims(final String token) {
    return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token);
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
      return true;
    } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
      throw new TokenNotValidException("잘못된 JWT 서명입니다.");
    } catch (ExpiredJwtException e) {
      throw new TokenNotValidException("만료된 JWT 토큰입니다.");
    } catch (UnsupportedJwtException e) {
      throw new TokenNotValidException("지원되지 않는 JWT 토큰입니다.");
    } catch (IllegalArgumentException e) {
      throw new TokenNotValidException("JWT 토큰이 잘못되었습니다.");
    }
  }
  // Jwt Token에서 User PK 추출
  public String getUserPk(String token) {
    return getPayload(token);
  }

  private String getResolvedToken(HttpServletRequest request, String tokenHeader) {
    String bearerToken = request.getHeader(tokenHeader);
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  public String resolveToken(HttpServletRequest servletRequest) {
    return getResolvedToken(servletRequest,AUTHORIZATION_HEADER);
  }
}
