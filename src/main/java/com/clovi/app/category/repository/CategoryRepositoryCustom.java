package com.clovi.app.category.repository;

import com.clovi.app.category.domain.Category;

import java.util.List;

public interface CategoryRepositoryCustom {
    List<Category> getAllCategory();
    List<Category> getChildCategoriesByParentId(Long id);
}
