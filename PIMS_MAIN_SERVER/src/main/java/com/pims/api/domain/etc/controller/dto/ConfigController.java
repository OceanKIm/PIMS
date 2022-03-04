package com.pims.api.domain.etc.controller.dto;


import com.pims.api.domain.etc.entity.ConfigEntity;
import com.pims.api.domain.etc.service.ConfigService;
import com.pims.api.exception.CustomResponseException;
import com.pims.api.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
     * : 설정 테이블 정보 단건 조회
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
        ConfigEntity configEntity;
        try {
            configEntity = configService.selectConfigInfo(id);

            // TODO :: entity to json

        } catch (CustomResponseException e) {
            return responseUtils.getResponse(e.getResultCode());
        } catch (Exception e) {
            log.error("controller error : {}", e.getMessage());
            return responseUtils.error500();
        }
        return responseUtils.getSuccess(configEntity);
    }


    /**
     * Controller
     * : 설정 테이블 정보 리스트 조회
     *
     * @authLevel 5
     * @method  GET
     * @uriPath /etc/config
     *
     * @return  org.springframework.http.ResponseEntity<?>
     */
    @RequestMapping(value = "/config", method = RequestMethod.GET)
    public ResponseEntity<?> selectConfigInfoList() {


        System.out.println("========================");


        return responseUtils.getSuccess();
    }


}
