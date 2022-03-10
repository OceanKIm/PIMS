package com.pims.api.domain.etc.controller.dto;


import com.pims.api.domain.etc.entity.Config;
import com.pims.api.domain.etc.service.ConfigService;

import com.pims.api.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * ConfigController
 * : 설정 관련 컨트롤러
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-03
**/
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("etc")
public class ConfigController {

    private final ResponseUtils responseUtils;

    private final ConfigService configService;

    /**
     * Controller
     * : 설정 테이블 정보 단건 조회 API
     *
     * @authLevel 5
     * @method  GET
     * @uriPath /etc/config/{id}
     *
     * @param   id config key 값
     * @return  org.springframework.http.ResponseEntity<?>
     */
    @RequestMapping(value = "/config/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> selectConfigInfo(@PathVariable Integer id) {
        Config config = configService.selectConfigInfo(id);
        // TODO check normal entity to json object
        return responseUtils.getSuccess(config);
    }

    /**
     * Controller
     * : 설정 테이블 정보 리스트 조회 API
     *
     * @authLevel 5
     * @method  GET
     * @uriPath /etc/config
     *
     * @return  org.springframework.http.ResponseEntity<?>
     */
    @RequestMapping(value = "/config", method = RequestMethod.GET)
    public ResponseEntity<?> selectConfigInfoList() {
        List<Config> resultList = configService.selectConfigInfo();
        return responseUtils.getSuccess(resultList);
    }
}
