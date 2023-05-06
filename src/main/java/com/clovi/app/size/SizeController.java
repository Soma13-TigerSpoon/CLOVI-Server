package com.clovi.app.size;

import com.clovi.app.size.dto.request.ItemSizeCreateRequest;
import com.clovi.app.base.dto.response.BaseResponse;
import com.clovi.app.base.dto.response.MessageCode;
import com.clovi.app.base.dto.response.ProcessStatus;
import com.clovi.app.member.Member;
import com.clovi.app.color.dto.response.ColorAndImgResponse;
import com.clovi.app.base.dto.response.SavedId;
import com.clovi.app.auth.helper.AuthMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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

@Tag(name = "[Size] 아이템 사이즈 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SizeController {
    private final SizeService sizeService;
    //저장
    @PostMapping("/info/items/{item_info_id}/size")
    @Operation(summary = "save itemSize", description = "save size of item", responses = {
            @ApiResponse(responseCode = "201", description = "Success create", content = @Content(schema = @Schema(implementation = SavedId.class)))
    })
    public ResponseEntity createItemSize(@Validated @RequestBody ItemSizeCreateRequest itemSizeCreateRequest, @AuthMember Member member, @PathVariable(name = "item_info_id") Long itemInfoId){
        SavedId savedId = new SavedId(sizeService.create(itemSizeCreateRequest,member,itemInfoId));
        return ResponseEntity.created(
                URI.create("/api/v1/info/items" + savedId.getId() + "/size")).body(new BaseResponse(savedId, HttpStatus.CREATED.value(), ProcessStatus.SUCCESS,
                MessageCode.SUCCESS_CREATE));
    }
    @GetMapping("/info/items/{item_info_id}/size")
    @Operation(summary = "find size by itemInfo", description = "Find all size of item by itemInfoID", responses = {
            @ApiResponse(responseCode = "200", description = "Success Find all size of items ", content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class))))
    })
    public ResponseEntity getAllSizeByItemInfo(@Validated @PathVariable(name = "item_info_id") Long itemInfoId) {
        List<String> response = sizeService.findAllColors(itemInfoId);
        return ResponseEntity.ok(
                new BaseResponse(response, HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET_LIST)
        );
    }
}
