package Soma.CLOVI.api.controller;

import Soma.CLOVI.api.response.BaseResponse;
import Soma.CLOVI.api.response.MessageCode;
import Soma.CLOVI.api.response.ProcessStatus;
import Soma.CLOVI.dto.response.CategoryResponseDto;
import Soma.CLOVI.service.CategoryService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping("/api/v1/categorys")
  public ResponseEntity getTopCategorysV1(){
    return ResponseEntity.ok(new BaseResponse(categoryService.getFirstCategorys(), HttpStatus.OK.value(), ProcessStatus.SUCCESS,
        MessageCode.SUCCESS_GET_LIST));
  }
  @GetMapping("/api/v1/categorys/{parent_id}")
  public ResponseEntity getChildCategorysV1(@PathVariable Long parent_id){
    List<CategoryResponseDto> result = categoryService.getChildCategorys(parent_id);
    if(result.isEmpty()){
      return ResponseEntity.badRequest().body(new BaseResponse(HttpStatus.BAD_REQUEST.value(), ProcessStatus.FAIL,MessageCode.ERROR_REQ_PARAM_CATEGORY_ID));
    }
    else{
      return ResponseEntity.ok(new BaseResponse(result,HttpStatus.OK.value(),ProcessStatus.SUCCESS,MessageCode.SUCCESS_GET_LIST));
    }

  }

}
