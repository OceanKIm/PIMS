package com.pims.api.cont;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Const
 * : server 상수 값 정의 클래스
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-02-26
**/
public class Const {

    public static final HashMap<String, Object> G_SERVER_CONFIG = new HashMap<>();

    /**
     * DB 설정테이블 키
     */
    public enum eCONFIG_KEY {
        ACCESS_TOKEN_EXP_HOUR,
        ACCESS_TOKEN_EXP_DAY,
        REFRESH_TOKEN_EXP_HOUR,
        PASSWORD_ENCRYPTION_ALGORITHM,
        IS_SERVER_AOP_LOG,
        IS_SERVER_AOP_REQ_LOG_HEADER,
        SUPPORT_IMG_EXTENSIONS
    }

    /**
     * 패스워드 알고리즘
     */
    public enum ePASSWORD_ENCRYPTION_ALGORITHM_TYPE {
        SHA256,
        MD5;
        public static String findName(String findName) {
            ePASSWORD_ENCRYPTION_ALGORITHM_TYPE eType = Arrays.stream(ePASSWORD_ENCRYPTION_ALGORITHM_TYPE.values()).filter(type -> findName.equals(type.name())).findAny().orElse(null);
            if (null == eType) {
                return null;
            }
            return eType.name();
        }
    }


    /**
     * 인중  헤더 타입 enum
     */
    public enum eHTTP_AUTH_HEADER {
        eAUTH_ACCESS_TOKEN("X-AUTH-ACCESS-TOKEN"),
        eAUTH_ID("X-AUTH-ID");

        private final String header;

        eHTTP_AUTH_HEADER(String authority) {
            this.header = authority;
        }

        public String getHeader() {
            return this.header;
        }

        public static String getHeader(String header) {
            eHTTP_AUTH_HEADER tbs = Arrays.stream(eHTTP_AUTH_HEADER.values()).filter(tb -> tb.header.equals(header)).findAny().orElse(null);
            if (tbs == null) {
                return null;
            }
            return tbs.getHeader();
        }
    }

    /**
     * JWT 토큰 발급 페이로드 키 enum
     */
    public enum eJWT_KEY {
        type,
        exp,
        level,
        inflow,
        applyNo
    }

    /**
     * 사용자 인증 타입 enum
     */
    public enum eSECURITY_AUTH_TYPE {
        eADMIN_AUTH_TYPE("AUTH_TYPE_ADMIN", 10),
        eUSER_AUTH_TYPE("AUTH_TYPE_USER", 1),
        eWEB_OS_AUTH_TYPE("AUTH_TYPE_WEB_OS", 1);


        private final String authority;
        private final int userLevel;

        eSECURITY_AUTH_TYPE(String authority, int userLevel) {
            this.authority = authority;
            this.userLevel = userLevel;
        }

        public String getAuthority() {
            return this.authority;
        }

        public int getUserLevel() {
            return this.userLevel;
        }

        public static String getAuthType(String authority) {
            eSECURITY_AUTH_TYPE tbs = Arrays.stream(eSECURITY_AUTH_TYPE.values()).filter(tb -> tb.authority.equals(authority)).findAny().orElse(null);
            if (tbs == null) {
                return null;
            }
            return tbs.getAuthority();
        }

        public static eSECURITY_AUTH_TYPE getAuthType(int userLevel) {
            return Arrays.stream(eSECURITY_AUTH_TYPE.values()).filter(tb -> tb.userLevel == userLevel).findAny().orElse(null);
        }
    }

    /**
     * ORDER BY TYPE ENUM
     */
    public enum eORDER_BY_TYPE {
        ASC("ASC"),
        DESC("DESC");

        private final String type;

        eORDER_BY_TYPE(String type) {
            this.type = type;
        }

        public static String findOrderType(String type) {
            eORDER_BY_TYPE enumType = Arrays.asList(eORDER_BY_TYPE.values()).stream().filter(tb -> tb.type.equals(type)).findAny().orElse(null);
            if (enumType == null) {
                return eORDER_BY_TYPE.ASC.type;
            }
            return enumType.type;
        }
    }

    /**
     * QNA ORDER BY ENUM
     */
    public enum eQNA_ORDER_BY {
        eREG("LGB_REG_DT", "reg"),
        eHIT("LGB_QNA_HIT_COUNT", "hit"),
        eComment("LGB_COMMENT_COUNT", "comment"),
        eRecommend("LGB_RECOMMEND_COUNT", "recommend"),
        eNotRecommend("LGB_NOT_RECOMMEND_COUNT", "notRecommend");

        private final String orderBy;
        private final String code;

        eQNA_ORDER_BY(String orderBy, String code) {
            this.orderBy = orderBy;
            this.code = code;
        }

        public static String findOrder(String code) {

            eQNA_ORDER_BY order = Arrays.asList(eQNA_ORDER_BY.values()).stream().filter(tb -> tb.code.equals(code)).findAny().orElse(null);

            if (order == null) {
                return eQNA_ORDER_BY.eREG.orderBy;
            }
            return order.orderBy;
        }
    }
}
