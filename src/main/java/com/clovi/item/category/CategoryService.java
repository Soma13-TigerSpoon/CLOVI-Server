package com.clovi.item.category;

import com.clovi.item.category.dto.response.CategoryResponseDto;
import com.clovi.item.category.repository.CategoryRepositoryCustomImpl;
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

  public List<CategoryResponseDto> getAllCategories() {
    return categoryRepositoryCustomImpl.getAllCategory().stream().
        map(category -> new CategoryResponseDto(category)).collect(Collectors.toList());
  }

  public List<CategoryResponseDto> getChildCategories(Long parentId) {
    return categoryRepositoryCustomImpl.getChildCategoriesByParentId(parentId).stream().
        map(category -> new CategoryResponseDto(category)).collect(Collectors.toList());
  }
}
