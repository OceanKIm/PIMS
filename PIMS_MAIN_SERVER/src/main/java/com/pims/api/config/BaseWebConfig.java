package com.pims.api.config;

import com.pims.api.interceptor.HttpInterceptor;
import com.pims.api.utils.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.*;

import javax.swing.filechooser.FileSystemView;

/**
 * BaseWebConfig
 * : 기본 웹 설정 클래스
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-06
**/
@Log4j2
@Configuration
public class BaseWebConfig implements WebMvcConfigurer {

    @Value("${SERVER.REQUEST.HEADERS}")
    private String mPropertiesRequestHeader;


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("forward:/docs/index.html");
//        registry.addViewController("/highlight/styles/github.min.css").setViewName("forward:/docs/highlight/styles/github.min.css");
//        registry.addViewController("/highlight/highlight.min.js").setViewName("forward:/docs/highlight/highlight.min.js");
//        registry.addViewController("/javadoc").setViewName("forward:/javadoc/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {

        CorsRegistration registration = registry.addMapping("/**");
        log.info("=================================================================");
        log.info("CROS REQUEST HEADER APPLY NAMES");
        log.info("=================================================================");
        for (String header : Utils.getRequestHeaders(mPropertiesRequestHeader)) {
            if (header.isEmpty()) continue;
            log.info(header);
            registration.allowedHeaders(header);
        }
        log.info("=================================================================");

        registration.allowedOriginPatterns("*")
                .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.HEAD.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name())
                .allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
        registry.addResourceHandler("/images/**").addResourceLocations("file:///" + rootPath + "/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

//    @Bean
//    public FilterRegistrationBean<RequestWrapperFilter> setFilterRegistration() {
//        FilterRegistrationBean<RequestWrapperFilter> filterRegistrationBean = new FilterRegistrationBean<>(new RequestWrapperFilter());
//        filterRegistrationBean.addUrlPatterns("/*");
//        return filterRegistrationBean;
//    }

}
