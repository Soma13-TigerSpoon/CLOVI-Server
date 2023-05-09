package com.clovi.app.color.repository;

import com.clovi.app.color.domain.Color;
import com.clovi.app.color.domain.ItemColor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemColorRepository extends JpaRepository<ItemColor, Long> {
    List<ItemColor> findAllByItemInfoId(Long itemInfoId);

    Optional<ItemColor> findByItemInfoIdAndColorIdAndImgUrlAndDeletedIsFalse(Long itemInfoId, Long colorId, String imgUrl);

    boolean existsByItemInfoIdAndColorIdAndImgUrlAndDeletedIsFalse(Long itemInfoId, Long colorId, String imgUrl);

}
