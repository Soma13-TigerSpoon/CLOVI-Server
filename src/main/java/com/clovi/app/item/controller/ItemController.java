package com.clovi.app.item.controller;

import com.clovi.app.base.dto.response.BaseResponse;
import com.clovi.app.base.dto.response.MessageCode;
import com.clovi.app.base.dto.response.ProcessStatus;
import com.clovi.app.item.service.ItemService;
import com.clovi.app.member.domain.Member;
import com.clovi.app.item.dto.request.ItemCreateRequest;
import com.clovi.app.item.dto.request.ItemUpdateRequest;
import com.clovi.app.base.dto.response.SavedId;
import com.clovi.app.item.dto.response.ItemResponse;
import com.clovi.app.shop.service.ShopService;
import com.clovi.app.auth.helper.AuthMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "[item] 유튜버가 입력하는 상품 색상 및 사이즈 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ItemController {

  private final ItemService itemService;

  // 상품 조회 API
  @GetMapping("/items/{item_id}")
  @Operation(summary = "Find item", description = "Find item by ID.", responses = {
          @ApiResponse(responseCode = "200", description = "Success find item", content = @Content(schema = @Schema(implementation = ItemResponse.class)))
  })
  public ResponseEntity findShopItemById(@PathVariable(name = "item_id") Long itemId){
    ItemResponse response = itemService.findItemById(itemId);
    return ResponseEntity.ok(new BaseResponse(response, HttpStatus.OK.value(), MessageCode.SUCCESS_GET));
  }

  // 상품 생성 API
  @PostMapping("/items")
  @Operation(summary = "Create item", description = "Create item and save", responses = {
          @ApiResponse(responseCode = "201", description = "Success create item", content = @Content(schema = @Schema(implementation = SavedId.class)))
  })
  public ResponseEntity createItem(@Validated @RequestBody ItemCreateRequest itemCreateRequest, @AuthMember Member member){
    SavedId savedId = new SavedId(itemService.create(itemCreateRequest,member));
    return ResponseEntity.created(
        URI.create("/api/v1/items" + savedId.getId())).body(new BaseResponse(savedId, HttpStatus.CREATED.value(),
        ProcessStatus.SUCCESS,
        MessageCode.SUCCESS_CREATE));
  }

  // 상품 수정 API
  @PutMapping("/items/{item_id}")
  @Operation(summary = "Update item", description = "Update item by id", responses = {
          @ApiResponse(responseCode = "200", description = "Success update item", content = @Content(schema = @Schema(implementation = SavedId.class)))
  })
  public ResponseEntity updateItem(@Validated @RequestBody ItemUpdateRequest itemUpdateRequest, @PathVariable(name = "item_id") Long itemId, @AuthMember Member member){
    SavedId savedId = new SavedId(itemService.update(itemUpdateRequest,itemId,member));
    return ResponseEntity.ok(new BaseResponse(savedId,HttpStatus.OK.value(),ProcessStatus.SUCCESS, MessageCode.SUCCESS_UPDATE));
  }

  // 상품 삭제 API
  @DeleteMapping("/items/{item_id}")
  @Operation(summary = "Delete item", description = "Delete item by id", responses = {
          @ApiResponse(responseCode = "200", description = "Success delete item")
  })
  public ResponseEntity deleteItem(@Validated @PathVariable(name = "item_id") Long itemId, @AuthMember Member member){
    itemService.delete(itemId,member);
    return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),ProcessStatus.SUCCESS, MessageCode.SUCCESS_DELETE));
  }

  @RestController
  @RequiredArgsConstructor
  public static class ShopController {
      private final ShopService shopService;

      @GetMapping("/api/v1/shops")
      public ResponseEntity getShopsV1(){
          return ResponseEntity.ok(new BaseResponse(shopService.getShops(), HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET));
      }

  }
}
