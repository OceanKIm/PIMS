package com.pims.api.domain.etc.service;

import com.pims.api.cont.Const;
import com.pims.api.domain.etc.mapper.ConfigMapper;
import com.pims.api.custom.CustomMap;
import com.pims.api.utils.Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * ConfigService
 * : 설정 관련 서비스
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-02
**/
@Service
@Log4j2
public class ConfigService {

    @Autowired
    private ConfigMapper configMapper;

    /**
     * 기본 설정값 조회 서버 글로벌 저장 Service
     */
    public void loadServerConfig() {
        ArrayList<CustomMap> resultArray = null;
        try {
            resultArray = configMapper.selectConfigInfoList();
            for (CustomMap map : resultArray) {
                String key = null;
                String value = null;
                if (map.containsKey("pims_key")) {
                    key = (String) map.get("pims_key");
                }
                if (map.containsKey("pims_value")) {
                    value = (String) map.get("pims_value");
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

    /**
     * 기본 설정값 조회 Service
     *
     * @return ArrayList 설정값
     */
    public ArrayList<CustomMap> selectConfigInfoList() {
        ArrayList<CustomMap> resultArray = null;
        try {
            resultArray = configMapper.selectConfigInfoList();
        } catch (Exception e) {
            log.error("======================================================");
            log.error("error : {}", e.getMessage());
            log.error("======================================================");
        }
        return resultArray;
    }

    /**
     * 단건 설정값 조회 Service
     *
     * @return ArrayList 설정값
     */
    public CustomMap selectConfigInfo(String key) {
        CustomMap resultMap = null;
        try {
            resultMap = configMapper.selectConfigInfo(key);
        } catch (Exception e) {
            log.error("======================================================");
            log.error("error : {}", e.getMessage());
            log.error("======================================================");
        }
        return resultMap;
    }

    /**
     * 설정값 수정 Service
     *
     * @param requestArray 설정값 array
     * @return int 설정값 업데이트 개수
     */
    public Integer updateConfigInfo(ArrayList<CustomMap> requestArray) {
        Integer configUpdateCount = -1;
        try {
            configUpdateCount = configMapper.updateConfigInfo(requestArray);

        } catch (Exception e) {
            log.error("======================================================");
            log.error("error : {}", e.getMessage());
            log.error("======================================================");
        }

        return configUpdateCount;
    }

}
