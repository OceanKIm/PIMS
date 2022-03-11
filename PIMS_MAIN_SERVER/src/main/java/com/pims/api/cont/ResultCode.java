package com.pims.api.cont;

/**
 * ResultCode
 * : response 결과 코드
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-04
**/
public enum ResultCode {

    //성공 또는 실패
    SUCCESS("S0000", MessageCode.RESULT_CODE_SUCCESS_MESSAGE),
    FAILURE("F0000", MessageCode.RESULT_CODE_FAIL_MESSAGE),

    // 요청오류
    BAD_REQUEST_ERROR("E1000", MessageCode.RESULT_CODE_BAD_REQUEST_MESSAGE),
    NOT_LOGIN_ERROR("E1001", MessageCode.RESULT_CODE_NOT_LOGIN_ERROR),
    COMMUNICATION_ERROR("E1002", MessageCode.RESULT_CODE_SERVER_COMMUNICATION_ERROR_MESSAGE),
    FORBIDDEN_ERROR("E1003", MessageCode.RESULT_CODE_FORBIDDEN_ERROR),
    NOT_FOUND_ERROR("E1004", MessageCode.RESULT_CODE_NOT_FOUND_MESSAGE),
    FILE_SIZE_ERROR_ERROR("E1005", MessageCode.RESULT_CODE_FILE_SIZE_ERROR),

    // 내부 오류
    SERVER_ERROR("E2000", MessageCode.RESULT_CODE_SERVER_ERROR_MESSAGE),
    SQL_FOREIGN_KEY_ERROR("E2001", MessageCode.RESULT_CODE_SQL_FOREIGN_KEY),
    UNKNOWN_ERROR("E2002", MessageCode.RESULT_CODE_UNKNOWN_ERROR),

    // 토큰 오류
    NOT_TOKEN_ERROR("E3000", MessageCode.RESULT_CODE_NOT_TOKEN_ERROR),
    MALFORMED_TOKEN_ERROR("E3001", MessageCode.RESULT_CODE_MALFORMED_TOKEN_ERROR),
    EXPIRED_TOKEN_ERROR("E3002", MessageCode.RESULT_CODE_EXPIRED_TOKEN_ERROR),
    UNSUPPORTED_TOKEN_ERROR("E3003", MessageCode.RESULT_CODE_UNSUPPORTED_TOKEN_ERROR),
    ARGUMENT_TOKEN_ERROR("E3004", MessageCode.RESULT_CODE_ARGUMENT_TOKEN_ERROR),

    // 인증 및 서비스 오류
    NO_AUTH_USER("E4000", MessageCode.RESULT_CODE_NO_AUTH_USER),
    VALID_EMPTY_USER("E4001", MessageCode.RESULT_CODE_VALID_EMPTY_USER),
    DUPLICATE_LOGIN("E4002", MessageCode.RESULT_CODE_DUPLICATE_LOGIN),
    NON_EXISTENT("E4003", MessageCode.RESULT_CODE_NON_EXISTENT_MESSAGE),
    VALID_DUPLICATE_USER("E4004", MessageCode.RESULT_CODE_VALID_DUPLICATE_USER),
    DUPLICATE_EMAIL("E4005", MessageCode.RESULT_CODE_DUPLICATE_EMAIL_ID),
    DUPLICATE_HP("E4006", MessageCode.RESULT_CODE_DUPLICATE_HP),
    NON_EXISTENT_EMAIL_ID("E4007", MessageCode.RESULT_CODE_NON_EXISTENT_EMAIL_ID);


    private final String code;
    private final MessageCode messageCode;

    ResultCode(String code, MessageCode messageCode) {
        this.code = code;
        this.messageCode = messageCode;
    }

    public String getCode() {
        return code;
    }

    public MessageCode getMessageCode() {
        return messageCode;
    }
}


