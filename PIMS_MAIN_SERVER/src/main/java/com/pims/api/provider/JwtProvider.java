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
import org.springframework.beans.factory.annotation.Autowired;
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

    //private final UserService userService;

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
     * @param authType   API 인증타입
     * @param inflowType 유입타입
     * @return TokenDTO 토큰 결과 DTO
     */
    public TokenDTO generateTokenDto(Const.eSECURITY_AUTH_TYPE authType, String inflowType) {

        Date now = new Date();
        int accessTokenHour = (int) Const.G_SERVER_CONFIG.get(Const.eCONFIG_KEY.ACCESS_TOKEN_EXP_HOUR.name());
        int refreshTokenHour = (int) Const.G_SERVER_CONFIG.get(Const.eCONFIG_KEY.ACCESS_TOKEN_EXP_HOUR.name());
        long accessTokenExpiration = (accessTokenHour * 60L) * 60 * 1000L;
        long refreshTokenExpiration = (refreshTokenHour * 60L) * 60 * 1000L;

        // Access Token 생성
        //Date accessTokenExpires = new Date(now.getTime() + (accessTokenExpiration * 60) * 60 * 1000L);
        Date accessTokenExpires = new Date(now.getTime() + accessTokenExpiration);
        String accessToken = Jwts.builder()
                .claim(Const.eJWT_KEY.type.name(), authType.getAuthority())
                .claim(Const.eJWT_KEY.exp.name(), accessTokenExpires)
                .claim(Const.eJWT_KEY.level.name(), authType.getUserLevel())
                .claim(Const.eJWT_KEY.inflow.name(), inflowType)
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
     * 커뮤니티 인증 토큰 DTO 만들기
     *
     * @param applyNo   베타 지원 일련번호
     * @return TokenDTO 토큰 결과 DTO
     */
    public TokenDTO generateCommunityAuthTokenDto(Integer applyNo) {

        Date now = new Date();
        int accessTokenDay = (int) Const.G_SERVER_CONFIG.get(Const.eCONFIG_KEY.ACCESS_TOKEN_EXP_DAY.name());
        int refreshTokenDay = (int) Const.G_SERVER_CONFIG.get(Const.eCONFIG_KEY.ACCESS_TOKEN_EXP_DAY.name());
        long accessTokenExpiration = (accessTokenDay * 24 * 60L) * 60 * 1000L;
        long refreshTokenExpiration = (refreshTokenDay * 24 * 60L) * 60 * 1000L;

        // Access Token 생성
        Date accessTokenExpires = new Date(now.getTime() + accessTokenExpiration);
        String accessToken = Jwts.builder()
                .claim(Const.eJWT_KEY.applyNo.name(), applyNo)
                .claim(Const.eJWT_KEY.exp.name(), accessTokenExpires)
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
        return request.getHeader(Const.eHTTP_AUTH_HEADER.eAUTH_ACCESS_TOKEN.getHeader());
    }

    /**
     * 토큰 인증 메소드
     *
     * @param request 요청
     * @param token   토큰
     * @return Authentication 인증 클래스
     */
    public Authentication getAuthentication(ServletRequest request, String token) {
        // 아이피
        String address = Utils.getIpAddress((HttpServletRequest) request);

        // 로그인 테이블 조회
        // 로그인 된 회원이면 인증 처리
        // 아이피 맵핑
        CustomMap customMap = new CustomMap();
        customMap.put("accessToken", token);
//      customMap.put("address", address);
        /*
        HashMap<String, Object> loginMap = userService.selectLoginInfo(customMap);
        if (null == loginMap) {
            throw new CustomForbiddenException(ResultCode.FORBIDDEN_ERROR);
        }
        if (loginMap.containsKey("LGB_LOGIN_ST")) {
            String userStatus = (String) loginMap.get("LGB_LOGIN_ST");
            if ("D".equals(userStatus)) {
                throw new CustomForbiddenException(ResultCode.NOT_LOGIN_ERROR);
            }
        }
        */
        int userLevel = (int) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get(Const.eJWT_KEY.level.name());
        Const.eSECURITY_AUTH_TYPE userAuthType = Const.eSECURITY_AUTH_TYPE.getAuthType(userLevel);
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userAuthType.getAuthority()));
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
