package com.pims.api.exception;

import com.pims.api.cont.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * CustomResponseException
 * : 임의로 Exception 발생 후 바로 API 응답시 사용
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-03
**/
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CustomResponseException extends RuntimeException {

    /**
     * 응답 전송 결과 코드
     */
    private ResultCode resultCode;

    public CustomResponseException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
