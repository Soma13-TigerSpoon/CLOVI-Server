package com.clovi.timeShopItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeShopItemRepository extends JpaRepository<TimeShopItem,Long> {

    boolean existsByTimeIdAndItemIdAndShopItemIdAndDeletedIsFalse(Long timeId, Long ItemId, Long shopItemId);

}
