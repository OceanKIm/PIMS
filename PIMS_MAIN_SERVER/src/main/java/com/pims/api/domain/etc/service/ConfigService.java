package com.pims.api.domain.etc.service;

import com.pims.api.cont.Const;
import com.pims.api.domain.etc.entity.ConfigEntity;
import com.pims.api.domain.etc.repository.ConfigRepository;
import com.pims.api.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ConfigService
 * : 설정 관련 서비스
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-02
**/
@RequiredArgsConstructor
@Service
@Log4j2
public class ConfigService {

    private final ConfigRepository configRepository;

    /**
     * 기본 설정값 조회 서버 글로벌 저장 Service
     */
    public void loadServerConfig() {
        String key = null;
        String value = null;
        List<ConfigEntity> resultList = configRepository.findAll();
        try {
            for (ConfigEntity config : resultList) {
                if (null != config.getPimsKey()) {
                    key = config.getPimsKey();
                }
                if (null != config.getPimsValue()) {
                    value = config.getPimsValue();
                }
                if (StringUtils.isEmpty(value)) {
                    log.warn("=====================================");
                    log.warn("{}, 값에  데이터가 존재하지 않습니다.", key);
                    log.warn("=====================================");
                    continue;
                }
                if (Utils.isNumber(value)) {
                    Const.G_SERVER_CONFIG.put(key, Integer.parseInt(value));
                } else {
                    Const.G_SERVER_CONFIG.put(key, value.trim());
                }
            }
        } catch (Exception e) {
            log.error("=============================================");
            log.error(e.getMessage());
            log.error("=============================================");
        }
    }

}
