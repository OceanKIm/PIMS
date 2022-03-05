package com.pims.api.interceptor;

import com.pims.api.resolver.CustomLocaleResolver;
import com.pims.api.utils.Utils;
import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HttpInterceptor
 * : controller 호출 전 후 인터셉터 클래스
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-05
**/
@Log4j2
public class HttpInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            // TODO check
            MDC.put("address", Utils.getIpAddress(request));

            // set locale context holder
            LocaleContextHolder.setLocale(CustomLocaleResolver.interceptResolveLocale(request));

        } catch (Exception e) {
            MDC.clear();
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }



}
