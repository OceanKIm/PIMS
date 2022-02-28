package com.pims.api.aop;

import com.pims.api.utils.Utils;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


/**
 * LoggerAspect
 * : 설명을 여기에 작성한다.
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-01
**/
@Component
@Aspect
@Log4j2
public class LoggerAspect {

    /**
     * AOP 로그 포인트컷 선언
     */
    @Pointcut("execution(*  com.lge.webos.lgb_main_server.api.controller..*Controller.*(..))") // 이런 패턴이 실행될 경우 수행
    public void loggerPointCut() {

    }

    /**
     * AOP 로그
     *
     * @param proceedingJoinPoint ProceedingJoinPointAOP Alliance를 래핑하는 AspectJ 인터페이스 의 구현
     * @return Object API 응답 값
     * @throws Throwable proceed 메소드 예외 처리
     */
    @Around("loggerPointCut()")
    public Object methodLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // call before
        // TODO :: logic

        // call
        ResponseEntity<?> result = (ResponseEntity<?>) proceedingJoinPoint.proceed();

        // call after
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Utils.showCallLog(proceedingJoinPoint.getSignature(), request, result);

        return result;
    }
}
