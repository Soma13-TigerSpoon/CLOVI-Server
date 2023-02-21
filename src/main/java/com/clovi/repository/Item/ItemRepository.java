package com.clovi.repository.Item;

import com.clovi.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Long> {
   // Optional<Item> findById(String itemId);
   // Optional<Item> findByName(String name);
   Optional<Item> findByNameAndBrand(String name,String brand);

   Optional<Item> findByIdAndIsDeletedIsFalse(Long itemId);
}
