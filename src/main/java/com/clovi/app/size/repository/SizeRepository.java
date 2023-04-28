package com.clovi.app.size.repository;

import com.clovi.app.size.domain.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SizeRepository extends JpaRepository<Size,Long> {
    Optional<Size> findByName(String name);
}
