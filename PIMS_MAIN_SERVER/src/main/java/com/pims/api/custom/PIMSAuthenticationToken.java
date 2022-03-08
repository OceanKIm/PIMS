package com.pims.api.custom;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * PIMSAuthenticationToken
 * : PIMS 인증 토큰 클래스
 * 
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-08
**/
public class PIMSAuthenticationToken extends AbstractAuthenticationToken {

    private final String jwtToken;
    private final String address;

    public PIMSAuthenticationToken(String jwtToken, String address, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.jwtToken = jwtToken;
        this.address = address;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return jwtToken;
    }

    @Override
    public Object getPrincipal() {
        return address;
    }
}
