package com.pims.api.custom;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

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
