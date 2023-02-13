package Soma.CLOVI.repository;

import Soma.CLOVI.domain.ManyToMany.ShopItem;
import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.domain.shop.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop,Long> {
    Optional<Shop> findByName(String shopName);
    List<Shop> findAll();

    Optional<Shop> findByHostname(String hostname);


    Optional<Shop> findByHostnameAndIsDeletedIsFalse(String hostname);
}
