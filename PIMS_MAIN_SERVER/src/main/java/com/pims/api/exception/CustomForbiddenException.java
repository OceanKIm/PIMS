package com.pims.api.exception;


import com.pims.api.cont.ResultCode;

/**
 * CustomForbiddenException
 * : HTTP 403 응답 에러 커스텀 예외처리
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-07
**/
public class CustomForbiddenException extends RuntimeException {

    private ResultCode resultCode;

    public CustomForbiddenException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
