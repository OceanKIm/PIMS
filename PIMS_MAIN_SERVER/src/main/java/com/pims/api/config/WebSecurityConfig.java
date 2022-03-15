package com.pims.api.config;

import com.pims.api.cont.Const;
import com.pims.api.filter.JwtAuthFilter;
import com.pims.api.provider.JwtProvider;
import com.pims.api.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


/**
 * WebSecurityConfig
 * : spring boot Security 설정 파일
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-07
**/
@Log4j2
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${SERVER.REQUEST.HEADERS}")
    private String mPropertiesRequestHeader;

    @Autowired
    private final JwtProvider jwtProvider;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String superAdmin = Const.USER_ROLE.superAdmin.name();
        String admin = Const.USER_ROLE.admin.name();
        String user = Const.USER_ROLE.user.name();

        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .cors().configurationSource(corsConfigurationSource()).and()
                .authorizeRequests()
                .antMatchers("/user/employee/join.do").permitAll()  // 회원가입 허용
                .antMatchers("/user/employee/login.do").permitAll() // 로그인 허용
                .antMatchers("/user/employee/refreshToken.do").permitAll() // 토큰 재발급 허용

                // 관리자 사용 API 권한 등록

                // 나머지 일반 사용자 권한 등록
                .anyRequest().hasRole(user)

                .and()
                .addFilterBefore(new JwtAuthFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * CORS 설정
     *
     * @return UrlBasedCorsConfigurationSource
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT"));
        configuration.setAllowedHeaders(Utils.getRequestHeaders(mPropertiesRequestHeader));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
