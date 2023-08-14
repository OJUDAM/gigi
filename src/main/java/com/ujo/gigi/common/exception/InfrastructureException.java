package com.ujo.gigi.common.exception;

/**
 * 외부 시스템 예외 처리
 * */
public class InfrastructureException extends  CustomException{

    public InfrastructureException(final String message, final ErrorCode errorCode) {
        super(message, errorCode);
    }
}
