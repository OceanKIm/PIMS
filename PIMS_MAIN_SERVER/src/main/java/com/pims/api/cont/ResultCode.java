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

    //성공
    SUCCESS("S0000", MessageCode.RESULT_CODE_SUCCESS_MESSAGE),

    // 요청오류
    BAD_REQUEST_ERROR("E4000", MessageCode.RESULT_CODE_BAD_REQUEST_MESSAGE),
    NOT_LOGIN_ERROR("E4001", MessageCode.RESULT_CODE_NOT_LOGIN_ERROR),
    COMMUNICATION_ERROR("E4002", MessageCode.RESULT_CODE_SERVER_COMMUNICATION_ERROR_MESSAGE),
    FORBIDDEN_ERROR("E4003", MessageCode.RESULT_CODE_FORBIDDEN_ERROR),
    NOT_FOUND_ERROR("E4004", MessageCode.RESULT_CODE_NOT_FOUND_MESSAGE),
    FILE_SIZE_ERROR_ERROR("E4005", MessageCode.RESULT_CODE_FILE_SIZE_ERROR),

    // 내부 오류
    SERVER_ERROR("E5000", MessageCode.RESULT_CODE_SERVER_ERROR_MESSAGE),
    SQL_FOREIGN_KEY_ERROR("E5001", MessageCode.RESULT_CODE_SQL_FOREIGN_KEY),
    UNKNOWN_ERROR("E5002", MessageCode.RESULT_CODE_UNKNOWN_ERROR),

    // 토큰 오류
    NOT_TOKEN_ERROR("E6001", MessageCode.RESULT_CODE_NOT_TOKEN_ERROR),
    MALFORMED_TOKEN_ERROR("E6002", MessageCode.RESULT_CODE_MALFORMED_TOKEN_ERROR),
    EXPIRED_TOKEN_ERROR("E6003", MessageCode.RESULT_CODE_EXPIRED_TOKEN_ERROR),
    UNSUPPORTED_TOKEN_ERROR("E6004", MessageCode.RESULT_CODE_UNSUPPORTED_TOKEN_ERROR),
    ARGUMENT_TOKEN_ERROR("E6005", MessageCode.RESULT_CODE_ARGUMENT_TOKEN_ERROR),

    // 인증 및 서비스 오류
    NO_AUTH_USER("E7001", MessageCode.NO_AUTH_USER),
    VALID_EMPTY_USER("E7003", MessageCode.VALID_EMPTY_USER),
    LIMITED_USER("E7002", MessageCode.LIMIT_USER),
    APPLY_ALREADY_RECR_COMPLETE("E7003", MessageCode.APPLY_ALREADY_RECR_COMPLETE),
    APPLY_NO_EXIST_DEVICE("E7004", MessageCode.APPLY_NO_EXIST_DEVICE),
    NO_RECRUITMENT_PERIOD("E7005", MessageCode.NO_RECRUITMENT_PERIOD),
    DUPLICATE_LOGIN("E7006", MessageCode.DUPLICATE_LOGIN),
    NON_EXISTENT("E7007", MessageCode.RESULT_CODE_NON_EXISTENT_MESSAGE),
    APPLY_NO_EXIST_DEVICE_AND_NO("E7008", MessageCode.APPLY_NO_EXIST_DEVICE_AND_NO),
    QNA_AUTH_FAILURE("E7008", MessageCode.QNA_AUTH_FAILURE);


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


