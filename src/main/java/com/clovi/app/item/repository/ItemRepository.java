package com.clovi.app.item.repository;

import java.util.List;
import java.util.Optional;

import com.clovi.app.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
  Optional<Item> findByIdAndDeletedIsFalse(Long itemId);

  Optional<Item> findByItemInfoIdAndDeletedIsFalse(Long itemInfoId);

  Optional<Item> findByItemInfoIdAndSizeAndDeletedIsFalse(Long itemInfoId, String size);

  List<Item> findAllByItemInfoIdAndDeletedIsFalse(Long id);
}
