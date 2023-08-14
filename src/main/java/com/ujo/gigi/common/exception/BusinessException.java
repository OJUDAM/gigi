package com.ujo.gigi.common.exception;

/**
 * 로직 오류 예외
 * */
public class BusinessException extends CustomException {
    public BusinessException(final String message, final ErrorCode errorCode) {
        super(message, errorCode);
    }
}
