package com.ujo.gigi.common.exception;

/**
 * 예외 발생 시 error-code 와 메시지 출력
 * */
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;

    public CustomException(final String message, final ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode(){
        return errorCode;
    }
}
