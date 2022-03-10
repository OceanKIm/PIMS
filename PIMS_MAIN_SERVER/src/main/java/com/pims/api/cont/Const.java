package com.pims.api.cont;

import com.pims.api.custom.CustomMap;

import java.util.Arrays;

/**
 * Const
 * : server 상수 값 정의 클래스
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-02-26
**/
public class Const {

    public static final CustomMap G_SERVER_CONFIG = new CustomMap();

    /**
     * DB 설정테이블 키
     */
    public enum CONFIG_KEY {
        ACCESS_TOKEN_EXP_HOUR,
        REFRESH_TOKEN_EXP_HOUR,
        PASSWORD_ENCRYPTION_ALGORITHM,
        IS_SERVER_AOP_LOG,
        IS_SERVER_AOP_REQ_LOG_HEADER,
        SUPPORT_IMG_EXTENSIONS
    }

    /**
     * 패스워드 알고리즘
     */
    public enum PASSWORD_ENCRYPTION_ALGORITHM_TYPE {
        SHA256,
        MD5;
        public static String findName(String findName) {
            PASSWORD_ENCRYPTION_ALGORITHM_TYPE eType = Arrays.stream(PASSWORD_ENCRYPTION_ALGORITHM_TYPE.values()).filter(type -> findName.equals(type.name())).findAny().orElse(null);
            if (null == eType) {
                return null;
            }
            return eType.name();
        }
    }


    /**
     * 인중 헤더 타입 enum
     */
    public enum HTTP_AUTH_HEADER {
        eAUTH_ACCESS_TOKEN("X-AUTH-ACCESS-TOKEN"),
        eAUTH_ID("X-AUTH-ID");

        private final String header;

        HTTP_AUTH_HEADER(String authority) {
            this.header = authority;
        }

        public String getHeader() {
            return this.header;
        }

        public static String getHeader(String header) {
            HTTP_AUTH_HEADER tbs = Arrays.stream(HTTP_AUTH_HEADER.values()).filter(tb -> tb.header.equals(header)).findAny().orElse(null);
            if (tbs == null) {
                return null;
            }
            return tbs.getHeader();
        }
    }

    /**
     * JWT 토큰 발급 페이로드 키 enum
     */
    public enum JWT_KEY {
        type,
        exp,
        level
    }

    /**
     * 사용자 권한 enum
     */
    public enum USER_ROLE {
        superAdmin(10),
        admin(5),
        user(1);

        private final int userLevel;

        USER_ROLE(int userLevel) {
            this.userLevel = userLevel;
        }
        public int getUserLevel() {
            return this.userLevel;
        }

        public static USER_ROLE getUserRole(int userLevel) {
            return Arrays.stream(USER_ROLE.values()).filter(tb -> tb.userLevel == userLevel).findAny().orElse(null);
        }

    }
}
