package com.clovi.app.itemInfo;

import com.clovi.app.itemInfo.dto.request.ItemInfoCreateRequest;
import com.clovi.app.itemInfo.dto.request.ItemInfoUpdateRequest;
import com.clovi.app.item.dto.response.ItemResponse;
import com.clovi.app.base.dto.response.BaseResponse;
import com.clovi.app.base.dto.response.MessageCode;
import com.clovi.app.base.dto.response.ProcessStatus;
import com.clovi.app.member.Member;
import com.clovi.app.base.dto.response.SavedId;
import com.clovi.app.auth.support.AuthMember;
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

@Tag(name = "[item_info] 상품 상세정보 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ItemInfoController {
  private final ItemInfoService itemInfoService;
  // private final ItemQueryService itemQueryService;

  // 상품 상세정보 조회 API
  @GetMapping("/v1/info/items/{item_info_id}")
  @Operation(summary = "Find itemInfo", description = "Find information of item by ID", responses = {
          @ApiResponse(responseCode = "200", description = "Success Find information of item", content = @Content(schema = @Schema(implementation = ItemResponse.class)))
  })
  public ResponseEntity getOneItem(@Validated @PathVariable(name = "item_info_id") Long itemInfoId) {
    ItemResponse result = itemInfoService.getItemByIdV1(itemInfoId);

    if(result == null) {
      return ResponseEntity.badRequest().body(
              new BaseResponse(HttpStatus.BAD_REQUEST.value(), ProcessStatus.FAIL, MessageCode.ERROR_REQ_PARAM_ITEM_ID)
      );
    }

    return ResponseEntity.ok(
            new BaseResponse(result, HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET)
    );
  }

  /* Deprecated
  @PostMapping("/v0/info/items")
  @Operation(summary = "Deprecated", description = "더 이상 안 써요 ")
  public ResponseEntity saveItemInfo(@Valid @RequestBody TimeShopItemRequestDto timeItemRequestDto) {
    // System.out.println(timeItemRequestDto);
    Long savedId = itemQueryService.save(timeItemRequestDto);

    return new ResponseEntity<>(
            new BaseResponse(new SavedId(savedId), HttpStatus.CREATED.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_CREATE),
            HttpStatus.CREATED
    );
  }
  */

  // 상품 상세정보 생성 API
  @PostMapping("/v1/info/items")
  @Operation(summary = "Create itemInfo", description = "Create information of item and save", responses = {
          @ApiResponse(responseCode = "201", description = "Success create", content = @Content(schema = @Schema(implementation = SavedId.class)))
  })
  public ResponseEntity createItemInfo(@Validated @RequestBody ItemInfoCreateRequest itemInfoCreateRequest, @AuthMember Member member){
    SavedId savedId = new SavedId(itemInfoService.create(itemInfoCreateRequest,member));
    return ResponseEntity.created(
        URI.create("/api/v1/info/items" + savedId.getId())).body(new BaseResponse(savedId, HttpStatus.CREATED.value(),ProcessStatus.SUCCESS,
        MessageCode.SUCCESS_CREATE));
  }

  // 상품 상세정보 수정 API
  @PutMapping("/v1/info/items/{item_info_id}")
  @Operation(summary = "Update itemInfo", description = "Update information of item", responses = {
          @ApiResponse(responseCode = "200", description = "Success update", content = @Content(schema = @Schema(implementation = SavedId.class)))
  })
  public ResponseEntity updateItemInfo(@Validated @RequestBody ItemInfoUpdateRequest itemInfoUpdateRequest, @PathVariable(name = "item_info_id") Long itemInfoId, @AuthMember Member member){
    SavedId savedId = new SavedId(itemInfoService.update(itemInfoUpdateRequest,itemInfoId,member));
    return ResponseEntity.ok(new BaseResponse(savedId,HttpStatus.OK.value(),ProcessStatus.SUCCESS, MessageCode.SUCCESS_UPDATE));
  }

  // 상품 상세정보 삭제 API
  @DeleteMapping("/v1/info/items/{item_info_id}")
  @Operation(summary = "Delete itemInfo", description = "Delete information of item", responses = {
          @ApiResponse(responseCode = "200", description = "Success delete")
  })
  public ResponseEntity deleteItemInfo(@Validated @PathVariable(name = "item_info_id") Long itemInfoId, @AuthMember Member member){
    itemInfoService.delete(itemInfoId,member);
    return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),ProcessStatus.SUCCESS, MessageCode.SUCCESS_DELETE));
  }

}
