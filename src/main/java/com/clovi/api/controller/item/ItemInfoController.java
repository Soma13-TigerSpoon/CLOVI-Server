package com.clovi.api.controller.item;

import com.clovi.api.response.BaseResponse;
import com.clovi.api.response.MessageCode;
import com.clovi.api.response.ProcessStatus;
import com.clovi.domain.user.Member;
import com.clovi.dto.requests.item.ItemInfoCreateRequest;
import com.clovi.dto.requests.item.ItemInfoDeleteRequest;
import com.clovi.dto.requests.item.ItemInfoUpdateRequest;
import com.clovi.dto.response.IdResponseDto;
import com.clovi.dto.requests.TimeItemRequestDto;
import com.clovi.dto.response.ItemResponseDto;
import com.clovi.service.item.ItemInfoService;
import com.clovi.service.query.ItemQueryService;
import com.clovi.support.auth.AuthMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import javax.validation.Valid;
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
          @ApiResponse(responseCode = "200", description = "Success Find information of item", content = @Content(schema = @Schema(implementation = ItemResponseDto.class)))
  })
  public ResponseEntity getOneItem(@Validated @PathVariable(name = "item_info_id") Long itemInfoId) {
    ItemResponseDto result = itemInfoService.getItemByIdV1(itemInfoId);

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
  public ResponseEntity saveItemInfo(@Valid @RequestBody TimeItemRequestDto timeItemRequestDto) {
    // System.out.println(timeItemRequestDto);
    Long savedId = itemQueryService.save(timeItemRequestDto);

    return new ResponseEntity<>(
            new BaseResponse(new IdResponseDto(savedId), HttpStatus.CREATED.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_CREATE),
            HttpStatus.CREATED
    );
  }
  */

  // 상품 상세정보 생성 API
  @PostMapping("/v1/info/items")
  @Operation(summary = "Create itemInfo", description = "Create information of item and save", responses = {
          @ApiResponse(responseCode = "201", description = "Success create", content = @Content(schema = @Schema(implementation = IdResponseDto.class)))
  })
  public ResponseEntity createItemInfo(@Validated @RequestBody ItemInfoCreateRequest itemInfoCreateRequest, @AuthMember Member member){
    IdResponseDto savedId = new IdResponseDto(itemInfoService.create(itemInfoCreateRequest,member));
    return ResponseEntity.created(
        URI.create("/api/v1/info/items" + savedId.getSavedId())).body(new BaseResponse(savedId, HttpStatus.CREATED.value(),ProcessStatus.SUCCESS,
        MessageCode.SUCCESS_CREATE));
  }

  // 상품 상세정보 수정 API
  @PutMapping("/v1/info/items/{item_info_id}")
  @Operation(summary = "Update itemInfo", description = "Update information of item", responses = {
          @ApiResponse(responseCode = "200", description = "Success update", content = @Content(schema = @Schema(implementation = IdResponseDto.class)))
  })
  public ResponseEntity updateItemInfo(@Validated @RequestBody ItemInfoUpdateRequest itemInfoUpdateRequest, @PathVariable(name = "item_info_id") Long itemInfoId,@AuthMember Member member){
    IdResponseDto savedId = new IdResponseDto(itemInfoService.update(itemInfoUpdateRequest,itemInfoId,member));
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
