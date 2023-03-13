package com.clovi.api.controller.item;

import com.clovi.api.response.BaseResponse;
import com.clovi.api.response.MessageCode;
import com.clovi.api.response.ProcessStatus;
import com.clovi.dto.response.CategoryResponseDto;
import com.clovi.service.CategoryService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping("/api/v1/categories")
  public ResponseEntity getAllCategoriesV1(){
    return ResponseEntity.ok(new BaseResponse(categoryService.getAllCategories(), HttpStatus.OK.value(), ProcessStatus.SUCCESS,
        MessageCode.SUCCESS_GET_LIST));
  }
  @GetMapping("/api/v1/categories/{parent_id}")
  public ResponseEntity getChildCategoriesV1(@PathVariable Long parent_id){
    List<CategoryResponseDto> result = categoryService.getChildCategories(parent_id);

      return ResponseEntity.ok(new BaseResponse(result,HttpStatus.OK.value(),ProcessStatus.SUCCESS,MessageCode.SUCCESS_GET_LIST));

  }

}
