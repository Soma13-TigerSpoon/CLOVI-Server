package com.clovi.app.shopItem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopItemRepository extends JpaRepository<ShopItem,Long> {
    Optional<ShopItem> findByShopItemUrl(String shopItemUrl);
    Optional<ShopItem> findByShopItemUrlAndPrice(String shopItemUrl, Long price);

  Optional<ShopItem> findByIdAndDeletedIsFalse(Long shopItemId);
}
