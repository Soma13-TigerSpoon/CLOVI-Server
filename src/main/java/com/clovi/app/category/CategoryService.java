package com.clovi.app.category;

import com.clovi.app.category.dto.response.CategoryResponse;
import com.clovi.app.category.repository.CategoryRepositoryCustomImpl;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepositoryCustomImpl categoryRepositoryCustomImpl;

  public List<CategoryResponse> getAllCategories() {
    return categoryRepositoryCustomImpl.getAllCategory().stream().
        map(category -> new CategoryResponse(category)).collect(Collectors.toList());
  }

  public List<CategoryResponse> getChildCategories(Long parentId) {
    return categoryRepositoryCustomImpl.getChildCategoriesByParentId(parentId).stream().
        map(category -> new CategoryResponse(category)).collect(Collectors.toList());
  }
}
