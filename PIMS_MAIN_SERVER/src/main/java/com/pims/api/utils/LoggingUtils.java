package com.pims.api.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pims.api.cont.Const;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.Signature;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * LoggingUtils
 * : 로깅 유틸 클래스
 * 
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-02
**/
@Log4j2
public class LoggingUtils {

    /**
     * 요청, 응답 로그 표현
     *
     * @param signature      요청 정보
     * @param request        요청 객체
     * @param responseEntity 응답 객체
     */
    public static void showCallLog(Signature signature, HttpServletRequest request, ResponseEntity<?> responseEntity) {
        showRequestLog(signature, request);
        showResponseLog(responseEntity);
    }


    /**
     * 요청 로그 표현
     *
     * @param signature 요청 정보
     * @param request   요청 객체
     */
    public static void showCallLog(Signature signature, HttpServletRequest request) {
        showRequestLog(signature, request);
    }


    /**
     * 응답 로그 표현
     *
     * @param responseEntity 응답 객체
     */
    public static void showCallLog(ResponseEntity<?> responseEntity) {
        showResponseLog(responseEntity);
    }

    /**
     * 요청 로그 비즈니스 로직
     *
     * @param signature 요청 정보
     * @param request   요청 객체
     */
    private static void showRequestLog(Signature signature, HttpServletRequest request) {
        boolean isServerAopLog = "Y".equals(Const.G_SERVER_CONFIG.get(Const.eCONFIG_KEY.IS_SERVER_AOP_LOG.name()));
        boolean isServerAopReqHeaderLog = "Y".equals(Const.G_SERVER_CONFIG.get(Const.eCONFIG_KEY.IS_SERVER_AOP_REQ_LOG_HEADER.name()));
        if (!isServerAopLog) return;

        String controllerName = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        Map<String, String> headersMap = Collections.list(request.getHeaderNames()).stream().collect(Collectors.toMap(name -> name, request::getHeader));

        try {
            if (isServerAopReqHeaderLog) {
                params.put("header", headersMap);
            }
            params.put("http_method", request.getMethod());
            params.put("request_url", request.getRequestURL());
            params.put("request_uri", request.getRequestURI());
            params.put("controller", controllerName);
            params.put("method", methodName);
            params.put("date", DateUtils.getCurrentTime(String.format("%s %s", DateUtils.DATE_PATTERN, DateUtils.TIME_PATTERN_SEC)));
            params.put("params", Utils.getParams(request));
        } catch (Exception e) {
            log.error("LoggerAspect error", e);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String strParams = gson.toJson(params).replace("\\\\\\", "").replace("\"[", "[").replace("]\"", "]");
        log.info("====================================================================================================");
        log.info(":: Request Info");
        log.info("====================================================================================================");
        log.info("params : \n" + strParams); // request 담긴 정보들을 한번에 로깅한다.
        log.info("====================================================================================================");
    }

    /**
     * 응답 로그 비즈니스 로직
     *
     * @param responseEntity 응답 객체
     */
    private static void showResponseLog(ResponseEntity<?> responseEntity) {
        boolean isServerAopLog = "Y".equals(Const.G_SERVER_CONFIG.get(Const.eCONFIG_KEY.IS_SERVER_AOP_LOG.name()));
        if (!isServerAopLog) return;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String strResult = gson.toJson(responseEntity);
        log.info("====================================================================================================");
        log.info(":: Response Info");
        log.info("====================================================================================================");
        log.info("result : \n" + Utils.decode(strResult)); // 결과 정보들을 한번에 로깅한다.
        log.info("====================================================================================================");

    }
}
