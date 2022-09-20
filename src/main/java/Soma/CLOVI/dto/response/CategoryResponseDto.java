package Soma.CLOVI.dto.response;

import Soma.CLOVI.domain.category.Category;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class CategoryResponseDto {

  private Long categoryId;
  private String categoryName;
  //private List<CategoryResponseDto> childCategory = new ArrayList<>();


  public CategoryResponseDto(Category category) {
    this.categoryId = category.getId();
    this.categoryName = category.getName();
  }
//  public void addChildCategory(Category category){
//    childCategory.add(new CategoryResponseDto(category));
//  }
}
