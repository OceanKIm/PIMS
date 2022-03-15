package com.pims.api.resolver;

import com.pims.api.cont.ResultCode;
import com.pims.api.exception.CustomForbiddenException;
import com.pims.api.exception.CustomResponseException;
import com.pims.api.utils.LoggingUtils;
import com.pims.api.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * ResponseExceptionResolver
 * : Exception 캐치를 통해 API response 전달
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-04
**/
@Log4j2
@EnableWebMvc
@RequiredArgsConstructor
@RestControllerAdvice
public class ResponseExceptionResolver {

    private final ResponseUtils responseUtils;

    /**
     *  일반 에러 처리 Exception
     *
     * @param e Exception
     * @return ResponseEntity<?> Error 응답
     */
    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<?> exception(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();    // TODO Debug 용 처리 코드
        ResponseEntity<?> response = responseUtils.getResponse(ResultCode.SERVER_ERROR);
        LoggingUtils.showCallLog(response);
        return response;
    }

    /**
     * 잘못된 URL 접급 Exception
     *
     * @param e       Exception
     * @param request 웹요청
     * @return ResponseEntity<?> Error 응답
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> notFoundException(NoHandlerFoundException e, WebRequest request) {
        log.error(e.getMessage());
        ResponseEntity<?> response = responseUtils.notFound(request);
        LoggingUtils.showCallLog(response);
        return response;
    }

    /**
     * 잘못된 요청 Exception
     *
     * @param e HttpMessageNotReadableException
     * @return ResponseEntity<?> Error 응답
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object httpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error(e.getMessage());
        ResponseEntity<?> response = responseUtils.getResponse(ResultCode.BAD_REQUEST_ERROR, HttpStatus.BAD_REQUEST);
        LoggingUtils.showCallLog(response);
        return response;
    }

    /**
     *  커스텀 마이징 HTTP 200 응답 처리 Exception
     *
     * @param e CustomResponseException
     * @return ResponseEntity<?> Error 응답
     */
    @ExceptionHandler(value = {CustomResponseException.class})
    public ResponseEntity<?> customResponseException(CustomResponseException e) {
        log.error(e.getMessage());
        ResponseEntity<?> response = responseUtils.getResponse(e.getResultCode());
        LoggingUtils.showCallLog(response);
        return response;
    }

    /**
     *  커스텀 마이징 HTTP 403 응답 처리 Exception
     *
     * @param e CustomForbiddenException
     * @return ResponseEntity<?> Error 응답
     */
    @ExceptionHandler(value = {CustomForbiddenException.class})
    public ResponseEntity<?> CustomForbiddenException(CustomForbiddenException e) {
        log.error(e.getMessage());
        ResponseEntity<?> response = responseUtils.getResponse(e.getResultCode(), HttpStatus.FORBIDDEN);
        LoggingUtils.showCallLog(response);
        return response;
    }

    /**
     *  JPA 처리 중 에러 응답 처리 Exception
     *
     * @param e ConstraintViolationException
     * @param e DataIntegrityViolationException
     * @return ResponseEntity<?> Error 응답
     */
    @ExceptionHandler(value = { ConstraintViolationException.class, DataIntegrityViolationException.class})
    protected ResponseEntity<?> handleDataException(Exception e) {
        log.error(e.getMessage());

        HashMap<String, String> resultMap = new HashMap<>();
        resultMap.put("cause", e.getLocalizedMessage());
        ResponseEntity<?> response = responseUtils.getResponse(resultMap, ResultCode.SERVER_ERROR, HttpStatus.OK);

        LoggingUtils.showCallLog(response);
        return response;
    }

    /**
     * DTO 유효성 검사 실패 Exception
     *
     * @param e ArgumentNotValidException
     * @return ResponseEntity<?> Error 응답
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e) {

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        ArrayList<HashMap<String, String>> errorList = new ArrayList<>();

        for (FieldError error : fieldErrors) {
            HashMap<String, String> map = new HashMap<>();
            map.put("field", error.getField());
            map.put("message", error.getDefaultMessage());
            errorList.add(map);
        }

        log.error(e.getMessage());
        log.error("{}", errorList);

        ResponseEntity<?> response = responseUtils.getResponse(errorList, ResultCode.BAD_REQUEST_ERROR, HttpStatus.BAD_REQUEST);

        LoggingUtils.showCallLog(response);
        return response;
    }
}