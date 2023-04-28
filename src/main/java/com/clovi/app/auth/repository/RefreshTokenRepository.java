package com.clovi.app.auth.repository;

import com.clovi.app.auth.domain.RefreshToken;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {

    Optional<RefreshToken> findByMemberId(Long memberId);
}
