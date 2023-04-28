package com.clovi.app.video.controller;

import com.clovi.app.base.dto.response.BaseResponse;
import com.clovi.app.base.dto.response.MessageCode;
import com.clovi.app.base.dto.response.ProcessStatus;
import com.clovi.app.base.dto.response.SavedId;
import com.clovi.app.video.dto.request.VideoRequest;
import com.clovi.app.video.dto.response.VideoResponse;
import com.clovi.app.video.service.VideoQueryService;
import com.clovi.app.video.service.VideoService;
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

@Tag(name = "[video] 유튜브 영상 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class VideoController {
  private final VideoService videoService;
  private final VideoQueryService videoQueryService;

  // Validation required
  @GetMapping("/videos")
  public ResponseEntity getVideoV1(@RequestParam("videoUrl") String videoUrl) {
    VideoResponse result = videoService.search(videoUrl);

    if(result == null) {
      return ResponseEntity.badRequest().body(
              new BaseResponse(HttpStatus.BAD_REQUEST.value(), ProcessStatus.FAIL, MessageCode.ERROR_REQ_PARAM_VIDEO_ID)
      );
    }

    return ResponseEntity.ok(
            new BaseResponse(result, HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET)
    );
  }

  @PostMapping("/videos")
  public ResponseEntity saveVideoV1(@RequestBody VideoRequest videoRequest) {
    Long result = videoQueryService.save(videoRequest);

    if(result == null) {
      return ResponseEntity.badRequest().body(
              new BaseResponse(HttpStatus.BAD_REQUEST.value(), ProcessStatus.FAIL, MessageCode.ERROR_REQ_PARAM_VIDEO_ID)
      );
    }

    return new ResponseEntity<>(
            new BaseResponse(new SavedId(result), HttpStatus.CREATED.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_CREATE),
            HttpStatus.CREATED
    );
  }

  @PostMapping("/video")
  @Operation(summary = "Create video", description = "Create video and save", responses = {
          @ApiResponse(responseCode = "201", description = "Success create", content = @Content(schema = @Schema(implementation = SavedId.class)))
  })
  public ResponseEntity saveVideo(@Validated @RequestBody VideoRequest videoRequest) {
    SavedId savedId = new SavedId(videoService.save(videoRequest));
    String newUrl = "/api/v1/video/".concat(savedId.getId().toString());

    return ResponseEntity.created(URI.create(newUrl)).body (
            new BaseResponse(savedId, HttpStatus.CREATED.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_CREATE)
    );
  }
}
