package Soma.CLOVI.dto.response;

import Soma.CLOVI.domain.category.Category;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class CategoryResponseDto {

  private Long categoryId;
  private String categoryName;

  private int order;
  private List<CategoryResponseDto> childCategories = new ArrayList<>();


  public CategoryResponseDto(Category category) {
    this.categoryId = category.getId();
    this.categoryName = category.getName();
    this.order = category.getOrders();
    for(Category child : category.getChildCategories()){
      childCategories.add(new CategoryResponseDto(child));
    }
  }
}
