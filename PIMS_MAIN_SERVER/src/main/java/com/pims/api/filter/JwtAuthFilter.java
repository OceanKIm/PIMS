package com.pims.api.filter;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pims.api.cont.Const;
import com.pims.api.cont.ResultCode;
import com.pims.api.domain.common.dto.ResponseDTO;
import com.pims.api.exception.CustomForbiddenException;
import com.pims.api.provider.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JwtAuthFilter
 * : 사용자 AccessToken 검증 필터
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-07
**/
@Log4j2
@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends GenericFilterBean {

    private final JwtProvider jwtProvider;

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        // 응답 객체 생성 시 활용
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        try {
            String token = jwtProvider.resolveAccessToken((HttpServletRequest) request);
            if (null != token && jwtProvider.isValidateToken(token)) {

                // Access 토큰인지 유효성 검사
                String type = jwtProvider.getTokenPayLoadInfo(token, Const.JWT_KEY.type.name());
                if (!Const.JWT_KEY.ACCESS_TOKEN.equals(type)) {
                    throw new CustomForbiddenException(ResultCode.NOT_TOKEN_TYPE_ERROR);
                }

                Authentication authentication = jwtProvider.getAuthentication(request, token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            chain.doFilter(request, response);
        } catch (CustomForbiddenException e) {
            setErrorResponse(httpServletResponse, e.getResultCode());
        }
    }

    public void setErrorResponse(HttpServletResponse response, ResultCode resultCode) {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatusMessage(HttpStatus.FORBIDDEN.getReasonPhrase());
        responseDTO.setResultCode(resultCode.getCode());
        responseDTO.setStatusCode(HttpStatus.FORBIDDEN.value());
        responseDTO.setMessage(jwtProvider.getMessageUtils().getMessage(resultCode.getMessageCode().name()));
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            response.getWriter().write(gson.toJson(responseDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}