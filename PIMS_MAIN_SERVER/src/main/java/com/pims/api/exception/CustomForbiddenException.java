package com.pims.api.exception;


import com.pims.api.cont.ResultCode;

/**
 * CustomForbiddenException
 * : 설명을 여기에 작성한다. TODO check
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
