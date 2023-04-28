package com.clovi.app.color.repository;

import com.clovi.app.color.domain.ItemColor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ItemColorRepository extends JpaRepository<ItemColor, Long> {
    List<ItemColor> findAllByItemInfoId(Long itemInfoId);
}
