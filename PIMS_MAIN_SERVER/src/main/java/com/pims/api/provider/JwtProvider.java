package com.pims.api.provider;

import com.pims.api.cont.Const;
import com.pims.api.cont.ResultCode;
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
import java.util.*;

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
    public TokenDTO generateTokenDto(Const.USER_ROLE userRole, Integer empNo) {

        Date now = new Date();
        int accessTokenExpMin = (int) Const.G_SERVER_CONFIG.get(Const.CONFIG_KEY.ACCESS_TOKEN_EXP_MIN.name());
        int refreshTokenExpMin = (int) Const.G_SERVER_CONFIG.get(Const.CONFIG_KEY.ACCESS_TOKEN_EXP_MIN.name());
        long accessTokenExpiration = (accessTokenExpMin * 60L) * 60 * 1000L;
        long refreshTokenExpiration = (refreshTokenExpMin * 60L) * 60 * 1000L;

        // Access Token 생성
        Date accessTokenExpires = new Date(now.getTime() + accessTokenExpiration);
        String accessToken = Jwts.builder()
                .claim(Const.JWT_KEY.type.name(), Const.JWT_KEY.ACCESS_TOKEN)
                .claim(Const.JWT_KEY.level.name(), userRole.getUserLevel())
                .claim(Const.JWT_KEY.exp.name(), accessTokenExpires)
                .claim(Const.JWT_KEY.empNo.name(), empNo)
                .setExpiration(accessTokenExpires)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        // Refresh Token 생성
        Date refreshTokenExpires = new Date(now.getTime() + refreshTokenExpiration);
        String refreshToken = Jwts.builder()
                .claim(Const.JWT_KEY.type.name(), Const.JWT_KEY.REFRESH_TOKEN)
                .claim(Const.JWT_KEY.level.name(), userRole.getUserLevel())
                .claim(Const.JWT_KEY.exp.name(), refreshTokenExpires)
                .claim(Const.JWT_KEY.empNo.name(), empNo)
                .setExpiration(refreshTokenExpires)
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
    public boolean isValidateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        } catch (SecurityException | MalformedJwtException | SignatureException e) {
            log.error("JWT 토큰의 기존 서명을 확인하지 못하였습니다.");
            throw new CustomForbiddenException(ResultCode.MALFORMED_TOKEN_ERROR);
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
            throw new CustomForbiddenException(ResultCode.EXPIRED_TOKEN_ERROR);
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
            throw new CustomForbiddenException(ResultCode.UNSUPPORTED_TOKEN_ERROR);
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
            throw new CustomForbiddenException(ResultCode.ARGUMENT_TOKEN_ERROR);
        }
        return true;
    }


    /**
     * 헤더에 Access 토큰 가져오기
     *
     * @param request 요청 객체 (HttpServletRequest)
     * @return 인증 DTO 리턴합니다.
     */
    public String resolveAccessToken(HttpServletRequest request) {
        return request.getHeader(Const.HTTP_AUTH_HEADER.AUTH_ACCESS_TOKEN.getHeader());
    }

    /**
     * 헤더에 Refresh 토큰 가져오기
     *
     * @param request 요청 객체 (HttpServletRequest)
     * @return 인증 DTO 리턴합니다.
     */
    public String resolveRefreshToken(HttpServletRequest request) {
        return request.getHeader(Const.HTTP_AUTH_HEADER.AUTH_REFRESH_TOKEN.getHeader());
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

        // 권한 가져오기
        Integer userLevel = (int) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get(Const.JWT_KEY.level.name());
        Const.USER_ROLE userRole = Const.USER_ROLE.getUserRole(userLevel);

        // 권한 체크 적용
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userRole.getAuthority()));

        // 사용자 일련번호 가져오기
        Integer empNo = (int) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get(Const.JWT_KEY.empNo.name());

        // 상세내용 생성
        HashMap<String, Object> details = new HashMap<>();
        details.put("address", address);
        details.put(Const.JWT_KEY.level.name(), userLevel);

        return new PIMSAuthenticationToken(empNo,token, details, authorities);
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
