package com.clovi.app.search;

import com.clovi.app.base.dto.response.BaseResponse;
import com.clovi.app.base.dto.response.MessageCode;
import com.clovi.app.base.dto.response.ProcessStatus;
import com.clovi.app.search.dto.request.SearchRequest;
import com.clovi.app.search.dto.response.KeywordResponse;
import com.clovi.app.search.dto.response.SearchResponse;
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
    public ResponseEntity searchByFilter(@Validated SearchRequest searchRequest,
                                         @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        SearchResponse result = searchService.getItemsAndVideosByFilter(searchRequest, pageable);

        return ResponseEntity.ok(
                new BaseResponse(result, HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET)
        );
    }

    @GetMapping("/keyword")
    public ResponseEntity searchByKeywordParam(@Validated @RequestParam(name = "query", required = false) String searchKeyword) {
        KeywordResponse result = searchService.getItemsAndVideosByKeyword(searchKeyword);

        return ResponseEntity.ok(
                new BaseResponse(result, HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET)
        );
    }

    @GetMapping("/keyword/{query}")
    public ResponseEntity searchByKeywordPath(@Validated @PathVariable(name = "query") String searchKeyword) {
        KeywordResponse result = searchService.getItemsAndVideosByKeyword(searchKeyword);

        return ResponseEntity.ok(
                new BaseResponse(result, HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET)
        );
    }
}
