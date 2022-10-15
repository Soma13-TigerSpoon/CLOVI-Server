package Soma.CLOVI.api.controller;

import Soma.CLOVI.api.response.BaseResponse;
import Soma.CLOVI.api.response.MessageCode;
import Soma.CLOVI.api.response.ProcessStatus;
import Soma.CLOVI.dto.requests.ClickRequestDto;
import Soma.CLOVI.dto.response.ClickResponseDto;
import Soma.CLOVI.service.LoggingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger("User Click Event!!");

    @PostMapping("/click")
    public ResponseEntity recordClickInfo(@RequestBody ClickRequestDto clickRequestDto) {
        ClickResponseDto result = loggingService.logClickTracking(clickRequestDto);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String clickInfo = mapper.writeValueAsString(result);
            logger.info(clickInfo);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        // String clickInfo = result.toString();
        // logger.info(clickInfo);

        return ResponseEntity.ok(
                new BaseResponse(HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET)
        );
    }
}
