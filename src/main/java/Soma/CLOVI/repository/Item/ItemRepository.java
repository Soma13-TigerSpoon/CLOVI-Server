package Soma.CLOVI.repository.Item;

import Soma.CLOVI.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
}
