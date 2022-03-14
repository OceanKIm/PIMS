package com.pims.api.custom;

import com.pims.api.utils.DateUtils;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * CustomMap
 * : LinkedHashMap 커스텀 클래스
 * 
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-02-25
**/
public class CustomMap extends LinkedHashMap<String, Object> {

    /**
     * 커스텀
     * : 문자열 값 입력시 trim 적용
     * : 날짜 타입 입력시 설정 포멧 적용
     * @param key 키값
     * @param value 벨류값
     * @return Object
    */
    @Override
    public CustomMap put(String key, Object value) {
        key = key.trim();
        if (value instanceof String) {
            value = ((String) value).trim();
        }
        if (value instanceof Date) {
            if ("java.sql.Timestamp".equals(value.getClass().getName())) {
                value = DateUtils.format((Date) value, DateUtils.DATE_PATTERN);
            } else {
                value = DateUtils.format((Date) value, DateUtils.DATE_PATTERN_FORMAT);
            }
        }
        super.put(key, value);
        return this;
    }
    
}
