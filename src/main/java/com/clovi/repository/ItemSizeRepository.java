package com.clovi.repository;

import com.clovi.domain.item.ItemSize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemSizeRepository extends JpaRepository<ItemSize,Long> {
    List<ItemSize> findAllByItemInfoId(Long itemInfoId);
}
