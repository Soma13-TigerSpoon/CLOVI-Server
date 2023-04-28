package com.clovi.app.color.controller;

import com.clovi.app.base.dto.response.BaseResponse;
import com.clovi.app.base.dto.response.MessageCode;
import com.clovi.app.base.dto.response.ProcessStatus;
import com.clovi.app.color.service.ColorService;
import com.clovi.app.color.dto.request.ItemColorCreateRequest;
import com.clovi.app.color.dto.response.ColorAndImgResponse;
import com.clovi.app.member.domain.Member;
import com.clovi.app.base.dto.response.SavedId;
import com.clovi.app.auth.helper.AuthMember;
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
import java.util.List;

@Tag(name = "[color] 아이템 색 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ColorController {

    private final ColorService colorService;
    //저장
    @PostMapping("/info/items/{item_info_id}/colors")
    @Operation(summary = "save itemColor", description = "save color of domain", responses = {
            @ApiResponse(responseCode = "201", description = "Success create", content = @Content(schema = @Schema(implementation = SavedId.class)))
    })
    public ResponseEntity createItemColor(@Validated @RequestBody ItemColorCreateRequest itemColorCreateRequest, @AuthMember Member member, @PathVariable(name = "item_info_id") Long itemInfoId){
        SavedId savedId = new SavedId(colorService.create(itemColorCreateRequest,member,itemInfoId));
        return ResponseEntity.created(
                URI.create("/api/v1/info/items" + savedId.getId() + "/colors")).body(new BaseResponse(savedId, HttpStatus.CREATED.value(), ProcessStatus.SUCCESS,
                MessageCode.SUCCESS_CREATE));
    }
    @GetMapping("/info/items/{item_info_id}/colors")
    @Operation(summary = "find colors by itemInfo", description = "Find information all colors of domain by itemInfoID", responses = {
            @ApiResponse(responseCode = "200", description = "Success Find colors of items ", content = @Content(schema = @Schema(implementation = ColorAndImgResponse.class)))
    })
    public ResponseEntity getAllColorItemInfo(@Validated @PathVariable(name = "item_info_id") Long itemInfoId) {
        List<ColorAndImgResponse> response = colorService.findAllColors(itemInfoId);
        return ResponseEntity.ok(
                new BaseResponse(response, HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET_LIST)
        );
    }

}
