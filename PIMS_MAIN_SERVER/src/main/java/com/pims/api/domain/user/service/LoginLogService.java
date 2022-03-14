package com.pims.api.domain.user.service;

import com.pims.api.domain.user.entity.LoginLog;
import com.pims.api.domain.user.repository.LoginLogRepository;
import com.pims.api.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * LoginLogService
 * : 로그인 로깅 처리 서비스.
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-14
**/
@RequiredArgsConstructor
@Service
@Log4j2
public class LoginLogService {

    private final LoginLogRepository loginLogRepository;

    public LoginLog insertLog(Integer empNo, String loginIp) {
        LoginLog loginLog = LoginLog.builder().empNo(empNo).loginIp(loginIp).loginDt(DateUtils.getCurrentDay(DateUtils.DATE_TIME_PATTERN)).build();
        return loginLogRepository.save(loginLog);
    }

}
