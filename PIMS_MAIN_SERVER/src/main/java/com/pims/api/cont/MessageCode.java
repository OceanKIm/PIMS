package com.pims.api.cont;

/**
 * MessageCode
 * : request 메시지 코드
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-02-24
**/
public enum MessageCode {

    RESULT_CODE_SUCCESS_MESSAGE,                                                         // 정상적으로 성공했습니다.
    RESULT_CODE_FAIL_MESSAGE,                                                            // 실패했습니다.
    RESULT_CODE_NOT_FOUND_MESSAGE,                                                       // 요청한 API 주소가 존재하지 않습니다.
    RESULT_CODE_BAD_REQUEST_MESSAGE,                                                     // 잘못된 요청입니다.
    RESULT_CODE_SERVER_ERROR_MESSAGE,                                                    // 서버로직 처리중 에러가 발생했습니다.
    RESULT_CODE_SERVER_COMMUNICATION_ERROR_MESSAGE,                                      // 통신 중 에러가 발생했습니다.
    RESULT_CODE_ALREADY_IN_USE_MESSAGE,                                                  // 이미 사용 중입니다.
    RESULT_CODE_NON_EXISTENT_MESSAGE,                                                    // 요청하신 정보는 존재하지 않습니다.
    RESULT_CODE_NON_EXISTENT_TOKEN_MESSAGE,                                              // 서버 인증 토큰이 존재하지 않습니다.
    RESULT_CODE_NON_EXISTENT_KEY_MESSAGE,                                                // 서버 인증 암호화 키가 존재하지 않습니다.
    RESULT_CODE_AUTH_KEY_GENERATE_SUCCESS_MESSAGE,                                       // API 키 발급 완료
    RESULT_CODE_AUTH_KEY_GENERATE_FAIL_MESSAGE,                                          // API 키 발급 실패
    RESULT_CODE_NOT_ADMIN_LOGIN,                                                         // 존재하지 않는 사용자 입니다.
    RESULT_CODE_FILE_SIZE_ERROR,                                                         // 파일 사이즈를 초과했습니다.
    RESULT_CODE_FORBIDDEN_ERROR,                                                         // 인증되지 않은 사용자 입니다.
    RESULT_CODE_NOT_LOGIN_ERROR,                                                         // 로그아웃 된 회원입니다.
    RESULT_CODE_NOT_TOKEN_ERROR,                                                         // 인증토큰이 존재하지 않습니다.
    RESULT_CODE_MALFORMED_TOKEN_ERROR,                                                   // 잘못된 토큰 서명입니다.
    RESULT_CODE_EXPIRED_TOKEN_ERROR,                                                     // 만료된 토큰입니다.
    RESULT_CODE_UNSUPPORTED_TOKEN_ERROR,                                                 // 지원하지 않는 토큰입니다.
    RESULT_CODE_ARGUMENT_TOKEN_ERROR,                                                    // 잘못된 토큰입니다.
    RESULT_CODE_SQL_FOREIGN_KEY,                                                         // 외래키 제약조건을 위배했습니다.
    RESULT_CODE_NOT_RESPONSE_DATA,                                                       // 데이터가 존재하지 않습니다.
    RESULT_CODE_NOT_USER_AUTH,                                                           // 인증되지 않은 사용자 입니다.
    RESULT_CODE_OVERLAP_PLATFORM_PACKAGE_NAME,                                           // 이미 등록한 패키지명이 있습니다.
    RESULT_CODE_NOT_IMAGE_FILE,                                                          // 지원하지 않는 파일입니다.
    RESULT_CODE_UNKNOWN_ERROR,                                                           // 알 수 없는 오류
    RESULT_CODE_FILE_NOT_FOUND,                                                                      // 파일이 존재하지 않습니다.
    RESULT_CODE_VALID_NOT_CONFIG_SETTING,                                                            // 잘못된 설정값 입니다.
    RESULT_CODE_VALID_DUPLICATE_USER,                                                                // 이미 등록된 회원입니다.
    RESULT_CODE_VALID_NOT_ENC_PASSWORD,                                                              // 패스워드 암호화 방식을 확인해 주세요.
    RESULT_CODE_VALID_EMPTY_INPUT_TARGET,                                                            // {0} 존재하지 않습니다.
    RESULT_CODE_VALID_EMPTY_USER,                                                                    // 회원이 존재하지 않습니다.
    RESULT_CODE_VALID_NOT_INFLOW,                                                                    // 잘못된 접근입니다.
    RESULT_CODE_VALID_EMPTY_TEMP,                                                                    // 해당 항목이 존재하지 않습니다.
    RESULT_CODE_NO_AUTH_USER,                                                                        // 권한이 없는 사용자 입니다.
    RESULT_CODE_DUPLICATE_LOGIN                                                                      // 중복 로그인 입니다.
}
