package com.pims.api.resolver;

import com.pims.api.utils.Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


/**
 * CustomLocaleResolver
 * : 커스텀 지역 코드 리졸버
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-02-26
**/
@Log4j2
@Component
public class CustomLocaleResolver extends AcceptHeaderLocaleResolver {

    @Value("${SERVER.MULTI.LANGUAGE}")
    private String mLanguageCode;

    private List<Locale> mLocales = Arrays.asList(new Locale("en"), new Locale("es"), new Locale("ko"));

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        // 언어팩 변경
        String acceptLanguage = request.getHeader("accept-language-pims");
        if (StringUtils.isEmpty(acceptLanguage)) {
            return Locale.getDefault();
        }
        log.info("================================================");
        log.info("Accept-Language : {}", acceptLanguage);
        log.info("================================================");
        List<Locale.LanguageRange> list = Locale.LanguageRange.parse(request.getHeader("accept-language-pims"));
        ArrayList<String> languageCodeArray = Utils.getPropertiesList(mLanguageCode);
        if (null != languageCodeArray) {
            mLocales = null;
            mLocales = new ArrayList<>();
            for (String code : languageCodeArray) {
                mLocales.add(new Locale(code));
            }
        }
        return Locale.lookup(list, mLocales);
    }

    public static Locale interceptResolveLocale(HttpServletRequest request) {

        // 언어팩 변경
        String acceptLanguage = request.getHeader("accept-language-pims");
        if (StringUtils.isEmpty(acceptLanguage)) {
            return Locale.getDefault();
        }

        List<Locale> settingLocales = Arrays.asList(new Locale("en"), new Locale("es"), new Locale("ko"));
        List<Locale.LanguageRange> list = Locale.LanguageRange.parse(request.getHeader("accept-language-pims"));

        log.info("================================================");
        log.info("Accept-Language : {}", acceptLanguage);
        log.info("================================================");
        return Locale.lookup(list, settingLocales);
    }

}
