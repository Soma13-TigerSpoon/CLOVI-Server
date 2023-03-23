package com.clovi.api.controller.shopItem;

import com.clovi.api.response.BaseResponse;
import com.clovi.api.response.MessageCode;
import com.clovi.api.response.ProcessStatus;
import com.clovi.domain.user.Member;
import com.clovi.dto.requests.ShopItemCreateRequest;
import com.clovi.dto.requests.ShopItemUpdateRequest;
import com.clovi.dto.response.IdResponseDto;
import com.clovi.dto.response.MemberResponse;
import com.clovi.dto.response.ShopItemResponseDto;
import com.clovi.service.item.ShopItemService;
import com.clovi.support.auth.AuthMember;
import io.swagger.v3.oas.annotations.Operation;
import java.net.URI;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
  public ResponseEntity createShopItem(@Validated @RequestBody ShopItemCreateRequest shopItemCreateRequest, @AuthMember Member member, @PathVariable(name = "item_info_id") Long itemInfoId){
    IdResponseDto savedId = new IdResponseDto(shopItemService.create(shopItemCreateRequest,member, itemInfoId));
    return ResponseEntity.created(
        URI.create(String.format("/api/v1/items/%d/shops/", itemInfoId) + savedId.getSavedId())).body(new BaseResponse(savedId, HttpStatus.CREATED.value(),ProcessStatus.SUCCESS,
        MessageCode.SUCCESS_CREATE));
  }
  @GetMapping("items/{item_info_id}/shops/{shop_item_id}")//쇼핑몰 링크 조회 API
  @Operation(summary = "쇼핑몰 링크 조회", description = "쇼핑몰 링크를 조회한다.", responses = {
          @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = MemberResponse.class)))
  })
  public ResponseEntity findShopItemById(@PathVariable(name = "item_info_id") Long itemInfoId, @PathVariable(name = "shop_item_id") Long shopItemId){
    ShopItemResponseDto response = shopItemService.findById(shopItemId);
    return ResponseEntity.ok(new BaseResponse(response, HttpStatus.OK.value(), MessageCode.SUCCESS_GET));
  }
  @PutMapping("items/{item_info_id}/shops/{shop_item_id}")//쇼핑몰 링크 수정 API
  @Operation(summary = "쇼핑몰 링크 수정", description = "쇼핑몰 링크를 수정한다.")
  public ResponseEntity updateShopItem(@Validated @RequestBody ShopItemUpdateRequest shopItemUpdateRequest, @AuthMember Member member, @PathVariable(name = "item_info_id") Long itemInfoId, @PathVariable(name = "shop_item_id") Long shopItemId){
    IdResponseDto savedId = new IdResponseDto(shopItemService.update(shopItemUpdateRequest,member, shopItemId));
    return ResponseEntity.ok(new BaseResponse(savedId,HttpStatus.OK.value(),ProcessStatus.SUCCESS, MessageCode.SUCCESS_UPDATE));
  }

  @DeleteMapping("/items/{item_info_id}/shops/{shop_item_id}")//쇼핑몰 링크 삭제 API
  @Operation(summary = "쇼핑몰 링크 삭제", description = "쇼핑몰 링크를 삭제한다.")
  public ResponseEntity deleteShopItem(@Validated @PathVariable(name = "shop_item_id") Long shopItemId, @AuthMember Member member, @PathVariable(name = "item_info_id") Long itemInfoId){
    shopItemService.delete(shopItemId, member, itemInfoId);
    return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),ProcessStatus.SUCCESS, MessageCode.SUCCESS_DELETE));
  }

}
