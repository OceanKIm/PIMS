package com.pims.api.custom;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashMap;

/**
 * PIMSAuthenticationToken
 * : PIMS 인증 토큰 클래스
 * 
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-08
**/
public class PIMSAuthenticationToken extends AbstractAuthenticationToken {

    private final Integer empNo;   // principal   (아이디  : 사원일련번호)
    private final String jwtToken; // credentials (비밀번호 : jwt token)

    /**
     * 상세 내용 details
     * 1. 접근 주소 : address
     * 2. 권한 레벨 : level
     */
    private final HashMap<String, Object> details;

    public PIMSAuthenticationToken(Integer empNo, String jwtToken, HashMap<String, Object> details, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.empNo = empNo;
        this.jwtToken = jwtToken;
        this.details = details;
        super.setAuthenticated(true);
    }

    @Override
    public Object getPrincipal() {
        return empNo;
    }

    @Override
    public Object getCredentials() {
        return jwtToken;
    }

    @Override
    public Object getDetails() {
        return details;
    }
}
