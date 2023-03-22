package com.clovi.api.controller.shopItem;

import com.clovi.api.response.BaseResponse;
import com.clovi.api.response.MessageCode;
import com.clovi.api.response.ProcessStatus;
import com.clovi.dto.requests.ShopItemCreateRequest;
import com.clovi.dto.requests.ShopItemDeleteRequest;
import com.clovi.dto.requests.ShopItemUpdateRequest;
import com.clovi.dto.response.IdResponseDto;
import com.clovi.service.item.ShopItemService;
import io.swagger.v3.oas.annotations.Operation;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ShopItemController {

  private final ShopItemService shopItemService;

  @PostMapping("items/{item_info_id}/shops")//쇼핑몰 링크 생성 API
  @Operation(summary = "쇼핑몰 링크 생성", description = "쇼핑몰 링크를 입력해 저장한다.")
  public ResponseEntity createShopItem(@Validated @RequestBody ShopItemCreateRequest shopItemCreateRequest, Long userId, @PathVariable(name = "item_info_id") Long itemInfoId){
    IdResponseDto savedId = new IdResponseDto(shopItemService.create(shopItemCreateRequest,userId, itemInfoId));
    return ResponseEntity.created(
        URI.create("/api/v1/shops/" + savedId.getSavedId())).body(new BaseResponse(savedId, HttpStatus.CREATED.value(),ProcessStatus.SUCCESS,
        MessageCode.SUCCESS_CREATE));
  }
  @PutMapping("items/{item_info_id}/shops")//쇼핑몰 링크 생성 API
  @Operation(summary = "쇼핑몰 링크 수정", description = "쇼핑몰 링크를 수정한다.")
  public ResponseEntity updateShopItem(@Validated @RequestBody ShopItemUpdateRequest shopItemUpdateRequest, Long userId, @PathVariable(name = "item_info_id") Long itemInfoId){
    IdResponseDto savedId = new IdResponseDto(shopItemService.update(shopItemUpdateRequest,userId, itemInfoId));
    return ResponseEntity.ok(new BaseResponse(savedId,HttpStatus.OK.value(),ProcessStatus.SUCCESS, MessageCode.SUCCESS_UPDATE));
  }

  @DeleteMapping("/items/{item_info_id}/shops")//쇼핑몰 링크 삭제 API
  @Operation(summary = "쇼핑몰 링크 삭제", description = "쇼핑몰 링크를 삭제한다.")
  public ResponseEntity deleteShopItem(@Validated @RequestBody ShopItemDeleteRequest shopItemDeleteRequest, Long userId, @PathVariable(name = "item_info_id") Long itemInfoId){
    shopItemService.delete(shopItemDeleteRequest,userId, itemInfoId);
    return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),ProcessStatus.SUCCESS, MessageCode.SUCCESS_DELETE));
  }

}
