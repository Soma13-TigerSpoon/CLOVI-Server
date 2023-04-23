package com.clovi.item.color.repository;

import com.clovi.item.color.domain.ItemColor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ItemColorRepository extends JpaRepository<ItemColor, Long> {
    List<ItemColor> findAllByItemInfoId(Long itemInfoId);
}
