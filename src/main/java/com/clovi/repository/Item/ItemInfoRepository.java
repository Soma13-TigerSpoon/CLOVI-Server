package com.clovi.repository.Item;

import com.clovi.domain.item.ItemInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemInfoRepository extends JpaRepository<ItemInfo,Long> {
   // Optional<ItemInfo> findById(String itemId);
   // Optional<ItemInfo> findByName(String name);
   Optional<ItemInfo> findByNameAndBrandAndColor(String name, String brand, String color);

   Optional<ItemInfo> findByIdAndDeletedIsFalse(Long itemId);
}
