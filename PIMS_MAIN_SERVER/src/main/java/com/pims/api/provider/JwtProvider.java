package com.pims.api.provider;

import com.pims.api.cont.Const;
import com.pims.api.custom.CustomMap;
import com.pims.api.custom.PIMSAuthenticationToken;
import com.pims.api.domain.common.dto.TokenDTO;
import com.pims.api.exception.CustomForbiddenException;
import com.pims.api.utils.MessageUtils;
import com.pims.api.utils.Utils;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Set;

/**
 * JwtProvider
 * : JWT 토큰 관련 클래스. 기본적인 작업만 되어 있어 추후 변경해서 사용
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-07
**/
@Log4j2
@RequiredArgsConstructor
@Component
public class JwtProvider {

    @Value("${SERVER.JWT.KEY}")
    private String JWT_KEY;
    private String secretKey = null;

    private final MessageUtils messageUtils;


    /**
     * 초기화
     */
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(JWT_KEY.getBytes());
    }

    /**
     * 로그인 인증 토큰 DTO 만들기
     *
     * @param userRole   API 인증타입
     * @return TokenDTO 토큰 결과 DTO
     */
    public TokenDTO generateTokenDto(Const.USER_ROLE userRole) {

        Date now = new Date();
        int accessTokenHour = (int) Const.G_SERVER_CONFIG.get(Const.CONFIG_KEY.ACCESS_TOKEN_EXP_HOUR.name());
        int refreshTokenHour = (int) Const.G_SERVER_CONFIG.get(Const.CONFIG_KEY.ACCESS_TOKEN_EXP_HOUR.name());
        long accessTokenExpiration = (accessTokenHour * 60L) * 60 * 1000L;
        long refreshTokenExpiration = (refreshTokenHour * 60L) * 60 * 1000L;

        // Access Token 생성
        Date accessTokenExpires = new Date(now.getTime() + accessTokenExpiration);
        String accessToken = Jwts.builder()
                .claim(Const.JWT_KEY.type.name(), userRole.name())
                .claim(Const.JWT_KEY.exp.name(), accessTokenExpires)
                .claim(Const.JWT_KEY.level.name(), userRole.getUserLevel())
                .setExpiration(accessTokenExpires)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now.getTime() + refreshTokenExpiration))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return TokenDTO.builder().accessToken(accessToken)
                .refreshToken(refreshToken)
                .expires(accessTokenExpires.getTime())
                .build();
    }

    /**
     * 토큰 유효성 검사
     *
     * @param token 인증토큰
     * @return boolean 유효성 결과
     */
    public boolean isValidateToken(String token) throws CustomForbiddenException {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    /**
     * 토큰 유효성 검사 MSG
     *
     * @param token 인증토큰
     * @return boolean 유효성 결과
     */
    public Integer isValidateTokenMsg(String token) throws CustomForbiddenException {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return 1;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
            return 2;
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
            return 3;
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
            return 4;
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
            return 5;
        } catch (Exception e){
            log.info("인증토큰이 존재하지 않습니다.");
            return 0;
        }
    }

    /**
     * 헤더에 토큰 가져오기
     *
     * @param request 요청 객체 (HttpServletRequest)
     * @return 인증 DTO 리턴합니다.
     */
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(Const.HTTP_AUTH_HEADER.eAUTH_ACCESS_TOKEN.getHeader());
    }

    /**
     * 토큰 인증 메소드
     *
     * @param request 요청
     * @param token   토큰
     * @return Authentication 인증 클래스
     */
    public Authentication getAuthentication(ServletRequest request, String token) {

        String address = Utils.getIpAddress((HttpServletRequest) request);
        Integer userLevel = (int) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get(Const.JWT_KEY.level.name());
        Const.USER_ROLE userRole = Const.USER_ROLE.getUserRole(userLevel);

        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userRole.getAuthority()));

        return new PIMSAuthenticationToken(token, address, authorities);
    }

    /**
     * 토큰 정보 가져오기 (토큰 정보 가져오기)
     *
     * @param token   토큰
     * @param key 키
     * @return
     */
    public <T> T getTokenPayLoadInfo(String token, String key) {
        Object value = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get(key);
        return (T)value;
    }

    /**
     * 토큰 전체 정보 가져오기 (토큰 정보 가져오기)
     *
     * @param token   토큰
     * @return
     */
    public CustomMap getTokenBodyInfo(String token) {
        CustomMap customMap = new CustomMap();
        Set<String> keySet = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().keySet();
        keySet.forEach( key -> {
            customMap.put(key, Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get(key));
        });
        return customMap;
    }

    /**
     * 메시지 유틸 가져오기
     *
     * @return MessageUtils
     */
    public MessageUtils getMessageUtils() {
        return messageUtils;
    }
}
