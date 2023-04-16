package com.clovi.api.controller.item;

import com.clovi.api.response.BaseResponse;
import com.clovi.api.response.MessageCode;
import com.clovi.api.response.ProcessStatus;
import com.clovi.domain.user.Member;
import com.clovi.dto.requests.item.ItemColorCreateRequest;
import com.clovi.dto.requests.item.ItemInfoCreateRequest;
import com.clovi.dto.response.ColorAndImgResponseDto;
import com.clovi.dto.response.IdResponseDto;
import com.clovi.dto.response.ItemResponseDto;
import com.clovi.service.item.ColorService;
import com.clovi.support.auth.AuthMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "[color] 아이템 색 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ColorController {

    private final ColorService colorService;
    //저장
    @PostMapping("/info/items/{item_info_id}/colors")
    @Operation(summary = "save itemColor", description = "save color of item", responses = {
            @ApiResponse(responseCode = "201", description = "Success create", content = @Content(schema = @Schema(implementation = IdResponseDto.class)))
    })
    public ResponseEntity createItemInfo(@Validated @RequestBody ItemColorCreateRequest itemColorCreateRequest, @AuthMember Member member, @PathVariable(name = "item_info_id") Long itemInfoId){
        IdResponseDto savedId = new IdResponseDto(colorService.create(itemColorCreateRequest,member,itemInfoId));
        return ResponseEntity.created(
                URI.create("/api/v1/info/items" + savedId.getSavedId() + "/colors")).body(new BaseResponse(savedId, HttpStatus.CREATED.value(), ProcessStatus.SUCCESS,
                MessageCode.SUCCESS_CREATE));
    }
    @GetMapping("/info/items/{item_info_id}/colors")
    @Operation(summary = "find colors by itemInfo", description = "Find information colors of item by itemInfoID", responses = {
            @ApiResponse(responseCode = "200", description = "Success Find colors of items ", content = @Content(schema = @Schema(implementation = ColorAndImgResponseDto.class)))
    })
    public ResponseEntity getColorsByItemInfo(@Validated @PathVariable(name = "item_info_id") Long itemInfoId) {
        return ResponseEntity.ok(
                new BaseResponse(colorService.findAllColors(itemInfoId), HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET_LIST)
        );
    }

}
