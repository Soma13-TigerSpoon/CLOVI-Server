package Soma.CLOVI.repository;

import Soma.CLOVI.domain.shop.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop,Long> {
}
