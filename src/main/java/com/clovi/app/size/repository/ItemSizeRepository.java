package com.clovi.app.size.repository;

import com.clovi.app.size.domain.ItemSize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemSizeRepository extends JpaRepository<ItemSize,Long> {
    List<ItemSize> findAllByItemInfoId(Long itemInfoId);
    Optional<ItemSize> findByItemInfoIdAndSizeIdAndDeletedIsFalse(Long itemInfoId, Long sizeId);

    boolean existsByItemInfoIdAndSizeIdAndDeletedIsFalse(Long itemInfoId, Long sizeId);
}
