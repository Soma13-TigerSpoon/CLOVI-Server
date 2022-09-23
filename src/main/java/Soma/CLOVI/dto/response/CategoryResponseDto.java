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
  private List<CategoryResponseDto> childCategory = new ArrayList<>();


  public CategoryResponseDto(Category category) {
    this.categoryId = category.getId();
    this.categoryName = category.getName();
    System.out.println(category);
    this.order = category.getOrders();
    System.out.println(category.getChildCategorys().size());
    for(Category child : category.getChildCategorys()){
      childCategory.add(new CategoryResponseDto(child));
    }
  }
}
