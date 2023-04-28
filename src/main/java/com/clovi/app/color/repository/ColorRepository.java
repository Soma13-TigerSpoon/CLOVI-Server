package com.clovi.app.color.repository;

import com.clovi.app.color.domain.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColorRepository extends JpaRepository<Color,Long> {
    Optional<Color> findByName(String name);
}
