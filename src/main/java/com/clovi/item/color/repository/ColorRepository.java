package com.clovi.item.color.repository;

import com.clovi.item.color.domain.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColorRepository extends JpaRepository<Color,Long> {
    Optional<Color> findByName(String name);
}
