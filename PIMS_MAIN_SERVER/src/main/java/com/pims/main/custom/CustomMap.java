package com.pims.main.custom;

import com.pims.main.utils.DateUtils;
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

    @Override
    public Object put(String key, Object value) {
        key = key.trim();
        if (value instanceof String) {
            value = ((String) value).trim();
        }
        if (value instanceof Date) {
            if ("java.sql.Timestamp".equals(value.getClass().getName())) {
                value = DateUtils.format((Date) value, DateUtils.TIMESTAMP_FORMAT2);
            } else {
                value = DateUtils.format((Date) value, DateUtils.DATE_PATTERN_FORMAT);
            }
        }
        super.put(key, value);
        return this;
    }
    
}
