package com.ujo.gigi.common.exception;

public class NotFoundException extends CustomException {
    public NotFoundException(final String message, final ErrorCode errorCode) {
        super(message, errorCode);
    }
}
