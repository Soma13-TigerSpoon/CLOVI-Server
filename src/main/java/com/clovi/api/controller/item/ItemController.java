package com.clovi.api.controller.item;

import com.clovi.api.response.BaseResponse;
import com.clovi.api.response.MessageCode;
import com.clovi.api.response.ProcessStatus;
import com.clovi.domain.user.Member;
import com.clovi.dto.requests.item.detail.ItemCreateRequest;
import com.clovi.dto.requests.item.detail.ItemDeleteRequest;
import com.clovi.dto.requests.item.detail.ItemUpdateRequest;
import com.clovi.dto.response.IdResponseDto;
import com.clovi.dto.response.ItemResponseDto;
import com.clovi.dto.response.MemberResponse;
import com.clovi.dto.response.ShopItemResponseDto;
import com.clovi.service.item.ItemService;
import com.clovi.support.auth.AuthMember;
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

@Tag(name = "유튜버가 입력하는 아이템 색,사이즈 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ItemController {

  private final ItemService itemService;

  @GetMapping("/items/{item_id}")//아이템 조회 API
  @Operation(summary = "Find item", description = "Find item by ID.", responses = {
          @ApiResponse(responseCode = "200", description = "Success Find item", content = @Content(schema = @Schema(implementation = ItemResponseDto.class)))
  })
  public ResponseEntity findShopItemById(@PathVariable(name = "item_id") Long itemId){
    ItemResponseDto response = itemService.findItemById(itemId);
    return ResponseEntity.ok(new BaseResponse(response, HttpStatus.OK.value(), MessageCode.SUCCESS_GET));
  }
  @PostMapping("/items")//아이템 생성 API
  @Operation(summary = "Create item", description = "Create item and save", responses = {
          @ApiResponse(responseCode = "201", description = "Success create", content = @Content(schema = @Schema(implementation = IdResponseDto.class)))
  })
  public ResponseEntity createItem(@Validated @RequestBody ItemCreateRequest itemCreateRequest, @AuthMember Member member){
    IdResponseDto savedId = new IdResponseDto(itemService.create(itemCreateRequest,member));
    return ResponseEntity.created(
        URI.create("/api/v1/items" + savedId.getSavedId())).body(new BaseResponse(savedId, HttpStatus.CREATED.value(),
        ProcessStatus.SUCCESS,
        MessageCode.SUCCESS_CREATE));
  }
  @PutMapping("/items/{item_id}")//아이템 수정 API
  @Operation(summary = "Update item", description = "Update item by id", responses = {
          @ApiResponse(responseCode = "200", description = "Success update", content = @Content(schema = @Schema(implementation = IdResponseDto.class)))
  })
  public ResponseEntity updateItem(@Validated @RequestBody ItemUpdateRequest itemUpdateRequest, @PathVariable(name = "item_id") Long itemId, @AuthMember Member member){
    IdResponseDto savedId = new IdResponseDto(itemService.update(itemUpdateRequest,itemId,member));
    return ResponseEntity.ok(new BaseResponse(savedId,HttpStatus.OK.value(),ProcessStatus.SUCCESS, MessageCode.SUCCESS_UPDATE));
  }
  @DeleteMapping("/items/{item_id}")//아이템 삭제 API
  @Operation(summary = "Delete item", description = "Delete item by id", responses = {
          @ApiResponse(responseCode = "200", description = "Success delete")
  })
  public ResponseEntity deleteItem(@Validated @PathVariable(name = "item_id") Long itemId, @AuthMember Member member){
    itemService.delete(itemId,member);
    return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),ProcessStatus.SUCCESS, MessageCode.SUCCESS_DELETE));
  }
}
