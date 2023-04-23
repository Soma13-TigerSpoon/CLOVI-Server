package com.clovi.repository.Item;

import com.clovi.domain.item.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColorRepository extends JpaRepository<Color,Long> {
    Optional<Color> findByName(String name);
}
