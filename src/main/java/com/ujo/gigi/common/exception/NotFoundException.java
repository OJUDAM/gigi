package com.ujo.gigi.common.exception;

/**
 * 404 에러 예외 처리
 * */
public class NotFoundException extends CustomException {
    public NotFoundException(final String message, final ErrorCode errorCode) {
        super(message, errorCode);
    }
}
