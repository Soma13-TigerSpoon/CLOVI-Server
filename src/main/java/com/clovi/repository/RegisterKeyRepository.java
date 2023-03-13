package com.clovi.repository;

import com.clovi.domain.user.RegisterKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterKeyRepository extends JpaRepository<RegisterKey,Long> {
    Optional<RegisterKey> findBySecretKeyAndDeletedIsFalse(String secretKey);
}
