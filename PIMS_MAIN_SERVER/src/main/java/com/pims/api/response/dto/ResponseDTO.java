package com.pims.api.response.dto;

import lombok.Data;

/**
 * ResponseDTO
 * : response 응답 객체 기본 틀
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-02-24
**/
@Data
public class ResponseDTO {

    private Integer statusCode;                                 // HTTP 상태 코드
    private String statusMessage;                               // HTTP 메시지
    private String resultCode;                                  // API  결과 코드
    private String message;                                     // API  메시지
    private Object res;                                         // API  응답 데이터

}
