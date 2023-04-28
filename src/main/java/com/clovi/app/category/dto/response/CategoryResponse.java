package com.clovi.app.category.dto.response;

import com.clovi.app.category.domain.Category;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class CategoryResponse {

  private Long categoryId;
  private String categoryName;

  private int order;
  private List<CategoryResponse> childCategories = new ArrayList<>();


  public CategoryResponse(Category category) {
    this.categoryId = category.getId();
    this.categoryName = category.getName();
    this.order = category.getOrders();
    for(Category child : category.getChildCategories()){
      childCategories.add(new CategoryResponse(child));
    }
  }
}
