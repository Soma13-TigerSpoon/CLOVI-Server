package Soma.CLOVI.repository.Category;

import Soma.CLOVI.domain.category.Category;
import java.util.List;

public interface CategoryRepositoryCustom {
    List<Category> getAllCategory();
    List<Category> getChildCategoriesByParentId(Long id);
}
