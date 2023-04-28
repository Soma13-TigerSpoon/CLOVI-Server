package com.clovi.app.shopItem;

import com.clovi.app.base.dto.response.BaseResponse;
import com.clovi.app.base.dto.response.MessageCode;
import com.clovi.app.base.dto.response.ProcessStatus;
import com.clovi.app.base.dto.response.SavedId;
import com.clovi.app.shopItem.dto.request.ShopItemCreateRequest;
import com.clovi.app.shopItem.dto.request.ShopItemUpdateRequest;
import com.clovi.app.shopItem.dto.response.ShopItemResponse;
import com.clovi.app.member.Member;
import com.clovi.app.member.dto.response.MemberResponse;
import com.clovi.app.auth.support.AuthMember;
import io.swagger.v3.oas.annotations.Operation;
import java.net.URI;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "[shop_item] 해당 상품을 판매하는 쇼핑몰 링크 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ShopItemController {

  private final ShopItemService shopItemService;

  // 쇼핑몰 링크 조회 API
  @GetMapping("/items/{item_info_id}/shops/{shop_item_id}")
  @Operation(summary = "Find shop link", description = "Find shop link by ID", responses = {
          @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = MemberResponse.class)))
  })
  public ResponseEntity findShopItemById(@PathVariable(name = "item_info_id") Long itemInfoId, @PathVariable(name = "shop_item_id") Long shopItemId) {
    ShopItemResponse response = shopItemService.findById(shopItemId);
    return ResponseEntity.ok(new BaseResponse(response, HttpStatus.OK.value(), MessageCode.SUCCESS_GET));
  }

  // 쇼핑몰 링크 생성 API
  @PostMapping("/items/{item_info_id}/shops")
  @Operation(summary = "Create shop link", description = "Save shop link of item", responses = {
          @ApiResponse(responseCode = "201", description = "Success create", content = @Content(schema = @Schema(implementation = SavedId.class)))
  })
  public ResponseEntity createShopItem(@Validated @RequestBody ShopItemCreateRequest shopItemCreateRequest, @AuthMember Member member, @PathVariable(name = "item_info_id") Long itemInfoId){
    SavedId savedId = new SavedId(shopItemService.create(shopItemCreateRequest,member, itemInfoId));
    return ResponseEntity.created(
        URI.create(String.format("/api/v1/items/%d/shops/", itemInfoId) + savedId.getId())).body(new BaseResponse(savedId, HttpStatus.CREATED.value(), ProcessStatus.SUCCESS,
        MessageCode.SUCCESS_CREATE));
  }

  // 쇼핑몰 링크 수정 API
  @PutMapping("/items/{item_info_id}/shops/{shop_item_id}")
  @Operation(summary = "Update shop link", description = "Update shop link", responses = {
          @ApiResponse(responseCode = "200", description = "Success update", content = @Content(schema = @Schema(implementation = SavedId.class)))
  })
  public ResponseEntity updateShopItem(@Validated @RequestBody ShopItemUpdateRequest shopItemUpdateRequest, @AuthMember Member member, @PathVariable(name = "item_info_id") Long itemInfoId, @PathVariable(name = "shop_item_id") Long shopItemId){
    SavedId savedId = new SavedId(shopItemService.update(shopItemUpdateRequest,member, shopItemId));
    return ResponseEntity.ok(new BaseResponse(savedId,HttpStatus.OK.value(),ProcessStatus.SUCCESS, MessageCode.SUCCESS_UPDATE));
  }

  // 쇼핑몰 링크 삭제 API
  @DeleteMapping("/items/{item_info_id}/shops/{shop_item_id}")
  @Operation(summary = "Delete shop link", description = "Delete shop link", responses = {
          @ApiResponse(responseCode = "200", description = "Success delete")
  })
  public ResponseEntity deleteShopItem(@Validated @PathVariable(name = "shop_item_id") Long shopItemId, @AuthMember Member member, @PathVariable(name = "item_info_id") Long itemInfoId){
    shopItemService.delete(shopItemId, member, itemInfoId);
    return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),ProcessStatus.SUCCESS, MessageCode.SUCCESS_DELETE));
  }

}
