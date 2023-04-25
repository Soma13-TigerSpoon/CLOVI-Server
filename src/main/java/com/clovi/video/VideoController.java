package com.clovi.video;

import com.clovi.base.dto.response.BaseResponse;
import com.clovi.base.dto.response.MessageCode;
import com.clovi.base.dto.response.ProcessStatus;
import com.clovi.base.dto.response.SavedId;
import com.clovi.video.dto.request.VideoRequest;
import com.clovi.video.dto.response.VideoResponseDto;
import com.clovi.video.service.VideoService;
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

  // Validation required
  @GetMapping("/videos")
  public ResponseEntity getVideoV1(@RequestParam("videoUrl") String videoUrl) {
    VideoResponseDto result = videoService.search(videoUrl);

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
  @Operation(summary = "Create video", description = "Create video and save", responses = {
          @ApiResponse(responseCode = "201", description = "Success create", content = @Content(schema = @Schema(implementation = SavedId.class)))
  })
  public ResponseEntity saveVideo(@Validated @RequestBody VideoRequest videoRequest) {
    SavedId savedId = new SavedId(videoService.save(videoRequest));
    String newUrl = "/api/v1/videos/".concat(savedId.getId().toString());

    return ResponseEntity.created(URI.create(newUrl)).body (
            new BaseResponse(savedId, HttpStatus.CREATED.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_CREATE)
    );
  }
}
