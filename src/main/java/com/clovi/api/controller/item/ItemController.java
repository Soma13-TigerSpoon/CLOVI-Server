package com.clovi.api.controller.item;

import com.clovi.api.response.BaseResponse;
import com.clovi.api.response.MessageCode;
import com.clovi.api.response.ProcessStatus;
import com.clovi.dto.requests.item.detail.ItemCreateRequest;
import com.clovi.dto.requests.item.detail.ItemDeleteRequest;
import com.clovi.dto.requests.item.detail.ItemUpdateRequest;
import com.clovi.dto.response.IdResponseDto;
import com.clovi.service.item.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유튜버가 입력하는 아이템 색,사이즈 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ItemController {

  private final ItemService itemService;
  @PostMapping("/items/detail")//아이템 상세 생성 API
  @Operation(summary = "아이템 상세 생성", description = "아이템 상세를 저장한다.")
  public ResponseEntity createItemDetail(@Validated @RequestBody ItemCreateRequest itemCreateRequest, Long userId){
    IdResponseDto savedId = new IdResponseDto(itemService.create(itemCreateRequest,userId));
    return ResponseEntity.created(
        URI.create("/api/v1/evaluation/" + savedId.getSavedId())).body(new BaseResponse(savedId, HttpStatus.CREATED.value(),
        ProcessStatus.SUCCESS,
        MessageCode.SUCCESS_CREATE));
  }
  @PutMapping("/items/detail")//아이템 상세 수정 API
  @Operation(summary = "아이템 상세 수정", description = "아이템 상세를 수정한다.")
  public ResponseEntity updateItemDetail(@Validated @RequestBody ItemUpdateRequest itemUpdateRequest, Long userId){
    IdResponseDto savedId = new IdResponseDto(itemService.update(itemUpdateRequest,userId));
    return ResponseEntity.ok(new BaseResponse(savedId,HttpStatus.OK.value(),ProcessStatus.SUCCESS, MessageCode.SUCCESS_UPDATE));
  }
  @DeleteMapping("/items/detail")//아이템 상세 삭제 API
  @Operation(summary = "아이템 상세 삭제", description = "아이템 상세를 삭제한다.")
  public ResponseEntity deleteItemDetail(@Validated @RequestBody ItemDeleteRequest itemDeleteRequest, Long userId){
    itemService.delete(itemDeleteRequest,userId);
    return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),ProcessStatus.SUCCESS, MessageCode.SUCCESS_DELETE));
  }
}
