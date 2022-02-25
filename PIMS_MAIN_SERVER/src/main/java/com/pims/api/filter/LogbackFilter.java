package com.pims.api.filter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;


/**
 * LogbackFilter
 * : 로그에서 해당 문자열을 포함하면 제외시킨다.
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-02-24
**/
public class LogbackFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (event.getMessage().contains("NOT_SQL_LOG")) {
            return FilterReply.DENY;
        }else{
            return FilterReply.ACCEPT;
        }
    }

}


