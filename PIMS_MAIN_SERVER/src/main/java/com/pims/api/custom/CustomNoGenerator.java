package com.pims.api.custom;

import org.hibernate.HibernateException;
import org.hibernate.boot.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.exception.spi.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.mariadb.jdbc.client.result.Result;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * CustomNoGenerator
 * : 테이블 NO 값 자동 생성을 위한 제너레이터
 * 
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-08
**/
public class CustomNoGenerator implements IdentifierGenerator, Configurable {

    // 속성값 처리를 위한 Const
    public static final String METHOD = "method";

    // 전달받은 속성값
    private String method;

    // 속성값 처리 메소드 override
    @Override
    public void configure(Properties params) throws HibernateException {
        method = ConfigurationHelper.getString(METHOD, params);
    }

    // ID 생성 처리 메소드 override
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) {
        return null;
    }
}
