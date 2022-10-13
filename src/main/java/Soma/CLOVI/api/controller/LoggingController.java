package Soma.CLOVI.api.controller;

import Soma.CLOVI.api.response.BaseResponse;
import Soma.CLOVI.api.response.MessageCode;
import Soma.CLOVI.api.response.ProcessStatus;
import Soma.CLOVI.dto.requests.ClickRequestDto;
import Soma.CLOVI.dto.response.ClickResponseDto;
import Soma.CLOVI.service.LoggingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LoggingController {
    private final LoggingService loggingService;

    @PostMapping("/click")
    public ResponseEntity saveClickInfo(@RequestBody ClickRequestDto clickRequestDto) {
        ClickResponseDto result = loggingService.logClickTracking(clickRequestDto);

        /*
        ObjectMapper mapper = new ObjectMapper();
        try {
            String log = mapper.writeValueAsString(result);
            System.out.println("log: " + log);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        */

        String log = result.toString();
        System.out.println("log: " + log);

        return ResponseEntity.ok(
                new BaseResponse(result, HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET)
        );
    }
}
