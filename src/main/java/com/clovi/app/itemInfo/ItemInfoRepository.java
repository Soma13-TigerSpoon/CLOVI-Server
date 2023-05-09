package com.clovi.app.itemInfo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemInfoRepository extends JpaRepository<ItemInfo,Long> {

   Optional<ItemInfo> findByIdAndDeletedIsFalse(Long itemId);
}
