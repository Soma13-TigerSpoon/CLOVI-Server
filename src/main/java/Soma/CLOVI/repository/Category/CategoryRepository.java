package Soma.CLOVI.repository.Category;

import Soma.CLOVI.domain.category.Category;
import Soma.CLOVI.domain.youtube.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
