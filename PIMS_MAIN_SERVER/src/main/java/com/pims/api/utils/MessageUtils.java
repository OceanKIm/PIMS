package com.pims.api.utils;


import com.pims.api.resolver.CustomLocaleResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * MessageUtils
 * 메시지 정보를 전달하는 유틸
 * 메시지는 코드로 구분 (다국어)
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-02-24
**/
@RequiredArgsConstructor
@Component
public class MessageUtils {

    private final MessageSource messageSource;

    private final CustomLocaleResolver customLocaleResolver;


    /**
     * 언어팩 언어 가져오기
     *
     * @param code 언어팩 코드
     * @return String
     */
    public String getMessage(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    /**
     * 언어팩 언어 가져오기 
     *
     * @param code 언어팩 코드
     * @param request 헤더에서 커스텀 지역 값 추출
     * @return String
     */
    public String getMessage(String code, HttpServletRequest request) {
        return messageSource.getMessage(code, null, customLocaleResolver.resolveLocale(request));
    }


    /**
     * 언어팩 언어 가져오기
     *
     * @param code 언어팩 코드
     * @param strs 동적 문자
     * @return String
     */
    public String getMessage(String code, String[] strs) {
        return messageSource.getMessage(code, strs, LocaleContextHolder.getLocale());
    }

}
