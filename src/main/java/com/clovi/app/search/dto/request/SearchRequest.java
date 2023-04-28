package com.clovi.app.search.dto.request;

import lombok.Data;

@Data
public class SearchRequest {
    private String keyword;
    private String channel;
    private long parentCategory;
    private long childCategory;
}
