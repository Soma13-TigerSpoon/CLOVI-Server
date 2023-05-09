package com.clovi.app.size.repository;

import com.clovi.app.size.domain.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SizeRepository extends JpaRepository<Size,Long> {
    Optional<Size> findByNameAndDeletedIsFalse(String name);

    boolean existsByNameAndDeletedIsFalse(String name);
}
