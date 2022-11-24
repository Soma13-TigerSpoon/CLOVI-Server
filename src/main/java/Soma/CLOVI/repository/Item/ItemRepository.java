package Soma.CLOVI.repository.Item;

import Soma.CLOVI.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Long> {
   // Optional<Item> findById(String itemId);
   // Optional<Item> findByName(String name);
   Optional<Item> findByNameAndColorAndBrand(String name, String color,String brand);
}
