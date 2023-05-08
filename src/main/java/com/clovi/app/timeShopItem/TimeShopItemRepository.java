package com.clovi.app.timeShopItem;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TimeShopItemRepository extends JpaRepository<TimeShopItem,Long> {
    Optional<TimeShopItem> findByTimeIdAndItemIdAndShopItemIdAndDeletedIsFalse(Long timeframeId, Long itemId, Long shopItemId);
    boolean existsByTimeIdAndItemIdAndShopItemIdAndDeletedIsFalse(Long timeId, Long ItemId, Long shopItemId);
}
