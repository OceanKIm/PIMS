package com.pims.api.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

/**
 * ArgumentNotValidException
 * : 유효성 검증 실패 Exception 클래스
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-04
**/
public class ArgumentNotValidException extends BindException {

    public ArgumentNotValidException(BindingResult bindingResult) {
        super(bindingResult);
    }
}
