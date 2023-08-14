package com.ujo.gigi.common.exception;

import lombok.Getter;

/**
 * 예외 발생 시 반환 할 객체
 * */
@Getter
public class ErrorResponse {

    private String errorCode;

    private ErrorResponse() {
    }

    private ErrorResponse(final String errorCode) {
        this.errorCode = errorCode;
    }

    public static ErrorResponse from(final ErrorCode errorCode) {
        return new ErrorResponse(errorCode.name());
    }
}
