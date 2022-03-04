package com.pims.api.utils;

import com.pims.api.cont.MessageCode;
import com.pims.api.cont.ResultCode;
import com.pims.api.common.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;


/**
 * ResponseUtils
 * : API response 대한 상태 및 결과 객첵 생성 클래스
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-02-26
**/
@Log4j2
@RequiredArgsConstructor
@Component
public class ResponseUtils {

    private final MessageUtils messageUtils;
    
    /**
     * response 응답값 (전달 데이터 있을 경우)
     *
     * @param res        응답데이터
     * @param resultCode 결과 코드
     * @param status     HTTP 상태
     * @return ResponseEntity
     */
    public ResponseEntity<ResponseDTO> getResponse(Object res, ResultCode resultCode, HttpStatus status) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setRes(res);
        responseDTO.setResultCode(resultCode.getCode());
        responseDTO.setMessage(messageUtils.getMessage(resultCode.getMessageCode().name()));
        responseDTO.setStatusMessage(status.getReasonPhrase());
        responseDTO.setStatusCode(status.value());
        return new ResponseEntity<>(responseDTO, status);
    }

    /**
     * response 응답값 (전달 데이터 없을 경우)
     *
     * @param resultCode 결과 코드
     * @param status     HTTP 상태
     * @return ResponseEntity
     */
    public ResponseEntity<ResponseDTO> getResponse(ResultCode resultCode, HttpStatus status) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResultCode(resultCode.getCode());
        responseDTO.setMessage(messageUtils.getMessage(resultCode.getMessageCode().name()));
        responseDTO.setStatusMessage(status.getReasonPhrase());
        responseDTO.setStatusCode(status.value());
        return new ResponseEntity<>(responseDTO, status);
    }

    /**
     * response 응답값 (HttpStatus ok)
     *
     * @param resultCode 결과 코드
     * @return ResponseEntity
     */
    public ResponseEntity<ResponseDTO> getResponse(ResultCode resultCode) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResultCode(resultCode.getCode());
        responseDTO.setMessage(messageUtils.getMessage(resultCode.getMessageCode().name()));
        responseDTO.setStatusMessage(HttpStatus.OK.getReasonPhrase());
        responseDTO.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * response 응답값 (헤더에서 지역값 추출)
     *
     * @param resultCode 결과 코드
     * @param request 헤더값 가져오기
     * @return ResponseEntity
     */
    public ResponseEntity<ResponseDTO> getResponse(ResultCode resultCode, HttpServletRequest request) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResultCode(resultCode.getCode());
        responseDTO.setMessage(messageUtils.getMessage(resultCode.getMessageCode().name(), request));
        responseDTO.setStatusMessage(HttpStatus.OK.getReasonPhrase());
        responseDTO.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * response 응답값 (전달 데이터 없을 경우) 메시지코드 활당
     *
     * @param resultCode  결과 코드
     * @param messageCode 메시지 코드
     * @param status      HTTP 상태
     * @return ResponseEntity
     */
    public ResponseEntity<ResponseDTO> getResponse(ResultCode resultCode, String messageCode, HttpStatus status) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResultCode(resultCode.getCode());
        responseDTO.setMessage(messageUtils.getMessage(messageCode));
        responseDTO.setStatusMessage(status.getReasonPhrase());
        responseDTO.setStatusCode(status.value());
        return new ResponseEntity<>(responseDTO, status);
    }

    /**
     * API CALL 성공 (response 있을경우)
     *
     * @param res response 데이터
     * @return ResponseEntity
     */
    public ResponseEntity<ResponseDTO> getSuccess(Object res) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setRes(res);
        responseDTO.setResultCode(ResultCode.SUCCESS.getCode());
        responseDTO.setMessage(messageUtils.getMessage(ResultCode.SUCCESS.getMessageCode().name()));
        responseDTO.setStatusMessage(HttpStatus.OK.getReasonPhrase());
        responseDTO.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * API CALL 성공 (response 없을 경우)
     *
     * @return ResponseEntity
     */
    public ResponseEntity<ResponseDTO> getSuccess() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResultCode(ResultCode.SUCCESS.getCode());
        responseDTO.setMessage(messageUtils.getMessage(ResultCode.SUCCESS.getMessageCode().name()));
        responseDTO.setStatusMessage(HttpStatus.OK.getReasonPhrase());
        responseDTO.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * 잘못된 API 요청의 대한 응답
     *
     * @param request 요청 URL 사용시 필요
     * @return ResponseEntity
     */
    public ResponseEntity<ResponseDTO> notFound(WebRequest request) {
        String requestUrl = ((ServletWebRequest) request).getRequest().getRequestURL().toString();
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResultCode(ResultCode.NOT_FOUND_ERROR.getCode());
        responseDTO.setMessage(messageUtils.getMessage(ResultCode.NOT_FOUND_ERROR.getMessageCode().name()));
        responseDTO.setStatusMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        responseDTO.setStatusCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * 서버 ERROR 200 (메세지 코드 할당)
     *
     * @param messageCode 메시지 코드
     * @return ResponseEntity
     */
    public ResponseEntity<ResponseDTO> error200(MessageCode messageCode) {
        return error200(messageUtils.getMessage(messageCode.name()));
    }

    /**
     * 서버 ERROR 400 (메세지 코드 할당)
     *
     * @param messageCode 메시지 코드
     * @return ResponseEntity
     */
    public ResponseEntity<ResponseDTO> error400(MessageCode messageCode) {
        return error400(messageUtils.getMessage(messageCode.name()));
    }

    /**
     * 서버 ERROR 400 (동적 문자열 할당)
     *
     * @param messageCode 메시지 코드
     * @param strings     치환될 문자 배열
     * @return ResponseEntity
     */
    public ResponseEntity<ResponseDTO> error400(MessageCode messageCode, String[] strings) {
        return error400(messageUtils.getMessage(messageCode.name(), strings));
    }

    /**
     * 서버 ERROR 200 (메세지 할당)
     *
     * @param message 메시지 String
     * @return ResponseEntity
     */
    public ResponseEntity<ResponseDTO> error200(String message) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResultCode(ResultCode.BAD_REQUEST_ERROR.getCode());
        responseDTO.setMessage(message);
        responseDTO.setStatusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        responseDTO.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * 서버 ERROR 400 (메세지 할당)
     *
     * @param message 메시지 String
     * @return ResponseEntity
     */
    public ResponseEntity<ResponseDTO> error400(String message) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResultCode(ResultCode.BAD_REQUEST_ERROR.getCode());
        responseDTO.setMessage(message);
        responseDTO.setStatusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    /**
     * 서버 ERROR 500
     *
     * @return ResponseEntity
     */
    public ResponseEntity<ResponseDTO> error500() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResultCode(ResultCode.SERVER_ERROR.getCode());
        responseDTO.setMessage(messageUtils.getMessage(ResultCode.SERVER_ERROR.getMessageCode().name()));
        responseDTO.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        responseDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
