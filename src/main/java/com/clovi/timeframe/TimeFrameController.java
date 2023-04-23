package com.clovi.timeframe;

import com.clovi.auth.support.AuthMember;
import com.clovi.base.dto.response.BaseResponse;
import com.clovi.base.dto.response.MessageCode;
import com.clovi.base.dto.response.ProcessStatus;
import com.clovi.base.dto.response.SavedId;
import com.clovi.member.Member;
import com.clovi.timeShopItem.dto.response.TimeShopItemResponseDto;
import com.clovi.timeframe.dto.request.TimeFrameCreateRequest;
import com.clovi.timeframe.dto.request.TimeFrameUpdateRequest;
import com.clovi.timeframe.dto.response.TimeFrameResponseDto;
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

@Tag(name = "[TimeFrame] 아이템 등장 시간 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TimeFrameController {
    private final TimeFrameService timeFrameService;

    // 영상에 대한 모든 시간 조회
    @GetMapping("/videos/{video_id}/timeframes")
    @Operation(summary = "Find all timeFrame", description = "Find all timeFrame by video ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Success Find timeFrame List", content = @Content(schema = @Schema(implementation = TimeFrameResponseDto.class)))
    })
    public ResponseEntity findAllTimeFramesByVideoId(@PathVariable(name = "video_id") Long video_id){
        List<TimeFrameResponseDto> response = timeFrameService.getTimeFrameListByVideoId(video_id);
        return ResponseEntity.ok(new BaseResponse(response, HttpStatus.OK.value(), MessageCode.SUCCESS_GET_LIST));
    }

    // 시간으로 해당 시간에 있는 아이템 리스트 조회 API
    @GetMapping("/videos/{video_id}/timeframes/{time_frame_id}")
    @Operation(summary = "Find timeFrame", description = "Find timeFrame by ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Success Find timeFrame", content = @Content(schema = @Schema(implementation = TimeShopItemResponseDto.class)))
    })
    public ResponseEntity findItemsByTimeFrameId(@PathVariable(name = "video_id") Long videoId, @PathVariable(name = "time_frame_id") Long timeFrameId){
        TimeShopItemResponseDto response = timeFrameService.getItemListByTimeFrameId(timeFrameId);
        return ResponseEntity.ok(new BaseResponse(response, HttpStatus.OK.value(), MessageCode.SUCCESS_GET));
    }

    // 시간 생성 API
    @PostMapping("/videos/{video_id}/timeframes")
    @Operation(summary = "Create timeFrame", description = "Create timeFrame and save", responses = {
            @ApiResponse(responseCode = "201", description = "Success create", content = @Content(schema = @Schema(implementation = SavedId.class)))
    })
    public ResponseEntity createTimeFrame(@PathVariable(name = "video_id") Long videoId,@Validated @RequestBody TimeFrameCreateRequest timeFrameCreateRequest, @AuthMember Member member){
        SavedId savedId = new SavedId(timeFrameService.create(timeFrameCreateRequest,videoId,member));
        return ResponseEntity.created(
                URI.create("/api/v1/timeFrames" + savedId.getId())).body(new BaseResponse(savedId, HttpStatus.CREATED.value(),
                ProcessStatus.SUCCESS,
                MessageCode.SUCCESS_CREATE));
    }

    // 시간 수정 API
    @PutMapping("/videos/{video_id}/timeframes/{time_frame_id}")
    @Operation(summary = "Update timeFrame", description = "Update timeFrame by id", responses = {
            @ApiResponse(responseCode = "200", description = "Success update", content = @Content(schema = @Schema(implementation = SavedId.class)))
    })
    public ResponseEntity updateTimeFrame(@Validated @RequestBody TimeFrameUpdateRequest timeFrameUpdateRequest, @PathVariable(name = "time_frame_id") Long timeFrameId, @AuthMember Member member, @PathVariable String video_id){
        SavedId savedId = new SavedId(timeFrameService.update(timeFrameUpdateRequest,timeFrameId,member));
        return ResponseEntity.ok(new BaseResponse(savedId,HttpStatus.OK.value(),ProcessStatus.SUCCESS, MessageCode.SUCCESS_UPDATE));
    }

    // 시간 삭제 API
    @DeleteMapping("/videos/{video_id}/timeframes/{time_frame_id}")
    @Operation(summary = "Delete timeFrame", description = "Delete timeFrame by id", responses = {
            @ApiResponse(responseCode = "200", description = "Success delete")
    })
    public ResponseEntity deleteTimeFrame(@Validated @PathVariable(name = "time_frame_id") Long timeFrameId, @AuthMember Member member, @PathVariable String video_id){
        timeFrameService.delete(timeFrameId,member);
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),ProcessStatus.SUCCESS, MessageCode.SUCCESS_DELETE));
    }
}
