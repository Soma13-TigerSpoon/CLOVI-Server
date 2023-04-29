package com.clovi.app.exception.video;
import com.clovi.app.exception.BadRequestException;

public class DuplicateVideoIdException extends BadRequestException {
    private static final String SERVER_MESSAGE = "이미 존재하는 영상입니다.";
    private static final String ERROR_CODE = "VIDEO_ID_EXISTS";
    private static final String CLIENT_MESSAGE = "이미 존재하는 영상입니다.";

    public DuplicateVideoIdException() {
        super(SERVER_MESSAGE, CLIENT_MESSAGE, ERROR_CODE);
    }
}
