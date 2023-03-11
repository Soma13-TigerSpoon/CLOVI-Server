package com.clovi.api.controller.item;

import com.clovi.api.response.BaseResponse;
import com.clovi.api.response.MessageCode;
import com.clovi.api.response.ProcessStatus;
import com.clovi.dto.requests.item.ItemInfoCreateRequest;
import com.clovi.dto.requests.item.ItemInfoDeleteRequest;
import com.clovi.dto.requests.item.ItemInfoUpdateRequest;
import com.clovi.dto.response.IdResponseDto;
import com.clovi.dto.requests.TimeItemRequestDto;
import com.clovi.dto.response.ItemResponseDto;
import com.clovi.service.item.ItemInfoService;
import com.clovi.service.query.ItemQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(name = "아이템 정보 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ItemInfoController {
  private final ItemInfoService itemInfoService;
  private final ItemQueryService itemQueryService;

  @GetMapping("/item/{item_id}")
  public ResponseEntity getOneItem(@Validated @PathVariable(name = "item_id") Long itemId) {
    ItemResponseDto result = itemInfoService.getItemById(itemId);

    if(result == null) {
      return ResponseEntity.badRequest().body(
              new BaseResponse(HttpStatus.BAD_REQUEST.value(), ProcessStatus.FAIL, MessageCode.ERROR_REQ_PARAM_ITEM_ID)
      );
    }

    return ResponseEntity.ok(
            new BaseResponse(result, HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET)
    );
  }

  @PostMapping("/items")
  public ResponseEntity saveItem(@Valid @RequestBody TimeItemRequestDto timeItemRequestDto) {
    // System.out.println(timeItemRequestDto);
    Long savedId = itemQueryService.save(timeItemRequestDto);

    return new ResponseEntity<>(
            new BaseResponse(new IdResponseDto(savedId), HttpStatus.CREATED.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_CREATE),
            HttpStatus.CREATED
    );
  }
  @PostMapping("/item")//아이템 생성 API
  @Operation(summary = "아이템 생성", description = "아이템을 직접 입력해 저장한다.")
  public ResponseEntity createItem(@Validated @RequestBody ItemInfoCreateRequest itemInfoCreateRequest, Long userId){
    IdResponseDto savedId = new IdResponseDto(itemInfoService.create(itemInfoCreateRequest,userId));
    return ResponseEntity.created(
        URI.create("/api/v1/evaluation/" + savedId.getSavedId())).body(new BaseResponse(savedId, HttpStatus.CREATED.value(),ProcessStatus.SUCCESS,
        MessageCode.SUCCESS_CREATE));
  }
  @PutMapping("/item")//아이템 수정 API
  @Operation(summary = "아이템 수정", description = "아이템을 직접 입력해 수정한다.")
  public ResponseEntity updateItem(@Validated @RequestBody ItemInfoUpdateRequest itemInfoUpdateRequest, Long userId){
    IdResponseDto savedId = new IdResponseDto(itemInfoService.update(itemInfoUpdateRequest,userId));
    return ResponseEntity.ok(new BaseResponse(savedId,HttpStatus.OK.value(),ProcessStatus.SUCCESS, MessageCode.SUCCESS_UPDATE));
  }
  @DeleteMapping("/item")//아이템 삭제 API
  @Operation(summary = "아이템 삭제", description = "아이템을 삭제한다.")
  public ResponseEntity deleteItem(@Validated @RequestBody ItemInfoDeleteRequest itemInfoDeleteRequest, Long userId){
    itemInfoService.delete(itemInfoDeleteRequest,userId);
    return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),ProcessStatus.SUCCESS, MessageCode.SUCCESS_DELETE));
  }

}
