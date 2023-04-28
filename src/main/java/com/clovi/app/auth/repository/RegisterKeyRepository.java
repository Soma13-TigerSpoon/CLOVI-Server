package com.clovi.app.auth.repository;

import com.clovi.app.auth.domain.RegisterKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterKeyRepository extends JpaRepository<RegisterKey,Long> {
    Optional<RegisterKey> findBySecretKeyAndDeletedIsFalse(String secretKey);
}
