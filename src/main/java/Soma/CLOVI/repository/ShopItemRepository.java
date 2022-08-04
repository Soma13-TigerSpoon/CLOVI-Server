package Soma.CLOVI.repository;

import Soma.CLOVI.domain.ShopItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopItemRepository extends JpaRepository<ShopItem,Long> {
}
