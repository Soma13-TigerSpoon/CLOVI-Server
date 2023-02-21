package com.clovi.repository.Category;

import com.clovi.domain.category.Category;
import java.util.List;

public interface CategoryRepositoryCustom {
    List<Category> getAllCategory();
    List<Category> getChildCategoriesByParentId(Long id);
}
