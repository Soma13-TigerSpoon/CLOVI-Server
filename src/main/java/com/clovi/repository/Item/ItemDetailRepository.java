package com.clovi.repository.Item;

import com.clovi.domain.item.ItemDetail;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDetailRepository extends JpaRepository<ItemDetail, Long> {
  Optional<ItemDetail> findByIdAndIsDeletedIsFalse(Long itemDetailId);
}
