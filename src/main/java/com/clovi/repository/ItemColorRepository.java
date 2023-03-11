package com.clovi.repository;

import com.clovi.domain.item.ItemColor;
import com.clovi.domain.item.ItemSize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ItemColorRepository extends JpaRepository<ItemSize, Long> {
    List<ItemColor> findAllByItemInfoId(Long itemInfoId);
}
