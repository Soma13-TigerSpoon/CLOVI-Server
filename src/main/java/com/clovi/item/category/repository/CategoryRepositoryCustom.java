package com.clovi.item.category.repository;

import com.clovi.item.category.Category;
import java.util.List;

public interface CategoryRepositoryCustom {
    List<Category> getAllCategory();
    List<Category> getChildCategoriesByParentId(Long id);
}
