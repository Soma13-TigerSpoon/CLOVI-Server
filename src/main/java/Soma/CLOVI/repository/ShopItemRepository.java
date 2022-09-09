package Soma.CLOVI.repository;

import Soma.CLOVI.domain.ManyToMany.ShopItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopItemRepository extends JpaRepository<ShopItem,Long> {
    Optional<ShopItem> findByShopUrl(String shopUrl);
}
