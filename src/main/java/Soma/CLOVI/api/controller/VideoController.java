package Soma.CLOVI.api.controller;

import Soma.CLOVI.api.response.BaseResponse;
import Soma.CLOVI.api.response.MessageCode;
import Soma.CLOVI.api.response.ProcessStatus;
import Soma.CLOVI.dto.use.*;
import Soma.CLOVI.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;

    @GetMapping("/api/video")
    public BaseResponse videoResponseV1(@RequestParam("videoUrl") String videoUrl){
        VideoResponseDto result = videoService.search(videoUrl);
        if(result == null){
            return new BaseResponse(HttpStatus.BAD_REQUEST,ProcessStatus.FAIL, MessageCode.ERROR_REQ_PARAM_VIDEO_ID);
        }
        return new BaseResponse(videoService.search(videoUrl), HttpStatus.OK, ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET);
    }
}
