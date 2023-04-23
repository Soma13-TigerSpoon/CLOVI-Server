package com.clovi.item.size.repository;

import com.clovi.item.size.domain.ItemSize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemSizeRepository extends JpaRepository<ItemSize,Long> {
    List<ItemSize> findAllByItemInfoId(Long itemInfoId);
}
