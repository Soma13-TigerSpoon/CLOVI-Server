package Soma.CLOVI.api.controller;

import Soma.CLOVI.dto.use.*;
import Soma.CLOVI.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;

    @GetMapping("/api/video")
    public AllDto videoResponseV1(@RequestParam("videoUrl") String videoUrl){
        return videoService.makeAllDto(videoUrl);
    }
}
