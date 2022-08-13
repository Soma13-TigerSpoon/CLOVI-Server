package Soma.CLOVI.repository;

import Soma.CLOVI.domain.ManyToMany.ShopItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopItemRepository extends JpaRepository<ShopItem,Long> {
}
