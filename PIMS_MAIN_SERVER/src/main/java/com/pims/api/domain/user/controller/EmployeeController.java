package com.pims.api.domain.user.controller;


import com.pims.api.domain.etc.entity.ConfigEntity;
import com.pims.api.domain.etc.service.ConfigService;
import com.pims.api.domain.user.controller.dto.EmployeeJoinDto;
import com.pims.api.domain.user.entity.EmployeeEntity;
import com.pims.api.domain.user.service.EmployeeService;
import com.pims.api.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * EmployeeController
 * : 사원 컨트롤러
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-08
**/
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class EmployeeController {

    private final ResponseUtils responseUtils;

    private final EmployeeService employeeService;

    /**
     * Controller
     * : 사용자 회원 가입 API
     *
     * @authLevel 1
     * @method  POST
     * @uriPath /user/employee
     *
     * @param   employeeJoinDto 회원가입 dto
     * @return  org.springframework.http.ResponseEntity<?>
     */
    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public ResponseEntity<?> joinEmployee(@RequestBody @Valid final EmployeeJoinDto employeeJoinDto) {

        EmployeeEntity employeeEntity = employeeService.joinEmployee(employeeJoinDto);

        return responseUtils.getSuccess(employeeEntity);
    }
}
