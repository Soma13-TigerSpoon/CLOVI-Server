package com.clovi.exception;

public class DuplicateResourceException extends BadRequestException {
    private static final String SERVER_MESSAGE = "이미 존재하는 정보 입니다.";
    private static final String ERROR_CODE = "RESOURCE_ALREADY_EXISTS";

    public DuplicateResourceException(String domain) {
        super(String.format("%s %s", domain, SERVER_MESSAGE), String.format("%s %s", domain, SERVER_MESSAGE), ERROR_CODE);
    }
}
