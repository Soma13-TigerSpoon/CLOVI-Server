package com.clovi.search;

import com.clovi.base.dto.response.BaseResponse;
import com.clovi.base.dto.response.MessageCode;
import com.clovi.base.dto.response.ProcessStatus;
import com.clovi.search.dto.request.SearchRequestDto;
import com.clovi.search.dto.response.KeywordResponseDto;
import com.clovi.search.dto.response.SearchResponseDto;
import com.clovi.search.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/search")
    public ResponseEntity searchByFilter(@Validated SearchRequestDto searchRequestDto,
                                         @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        SearchResponseDto result = searchService.getItemsAndVideosByFilter(searchRequestDto, pageable);

        return ResponseEntity.ok(
                new BaseResponse(result, HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET)
        );
    }

    @GetMapping("/keyword")
    public ResponseEntity searchByKeywordParam(@Validated @RequestParam(name = "query", required = false) String searchKeyword) {
        KeywordResponseDto result = searchService.getItemsAndVideosByKeyword(searchKeyword);

        return ResponseEntity.ok(
                new BaseResponse(result, HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET)
        );
    }

    @GetMapping("/keyword/{query}")
    public ResponseEntity searchByKeywordPath(@Validated @PathVariable(name = "query") String searchKeyword) {
        KeywordResponseDto result = searchService.getItemsAndVideosByKeyword(searchKeyword);

        return ResponseEntity.ok(
                new BaseResponse(result, HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET)
        );
    }
}
