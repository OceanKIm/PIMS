package com.pims.api.domain.user.repository;

import com.pims.api.domain.user.entity.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * LoginLogRepository
 * : 로그인 로깅 처리 레파지토리
 * 
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-14
**/
public interface LoginLogRepository extends JpaRepository<LoginLog, Integer>, JpaSpecificationExecutor<LoginLog> {

}