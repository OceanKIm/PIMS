package com.pims.api.domain.common.dto;

import lombok.Data;

/**
 * TokenDTO
 * : 토큰정보를 담는 DTO 클래스
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-07
**/
@Data
public class TokenDTO {

    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpires;

    public TokenDTO(Builder builder) {
        setAccessToken(builder.accessToken);
        setRefreshToken(builder.refreshToken);
        setAccessTokenExpires(builder.accessTokenExpires);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String accessToken;
        private String refreshToken;
        private Long accessTokenExpires;

        public Builder accessToken(String token) {
            accessToken = token;
            return this;
        }

        public Builder refreshToken(String token) {
            refreshToken = token;
            return this;
        }

        public Builder expires(Long expires) {
            accessTokenExpires = expires;
            return this;
        }

        public TokenDTO build() {
            return new TokenDTO(this);
        }
    }
}
