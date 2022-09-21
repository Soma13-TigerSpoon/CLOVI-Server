package Soma.CLOVI.service;

import Soma.CLOVI.dto.response.CategoryResponseDto;
import Soma.CLOVI.dto.response.ItemResponseDto;
import Soma.CLOVI.repository.Category.CategoryRepositoryImpl;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepositoryImpl categoryRepositoryImpl;

  public List<CategoryResponseDto> getAllCategorys() {
    return categoryRepositoryImpl.getAllCategory().stream().
        map(category -> new CategoryResponseDto(category)).collect(Collectors.toList());
  }

  public List<CategoryResponseDto> getChildCategorys(Long parentId) {
    return categoryRepositoryImpl.getChildCategoryById(parentId).stream().
        map(category -> new CategoryResponseDto(category)).collect(Collectors.toList());
  }

}
