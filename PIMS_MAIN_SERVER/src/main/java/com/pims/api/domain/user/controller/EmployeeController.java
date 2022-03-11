package com.pims.api.domain.user.controller;


import com.pims.api.cont.ResultCode;
import com.pims.api.domain.user.controller.dto.EmployeeJoinDto;
import com.pims.api.domain.user.controller.dto.EmployeeLoginDto;
import com.pims.api.domain.user.service.EmployeeService;
import com.pims.api.domain.user.service.UserInfoService;
import com.pims.api.utils.EncryptUtil;
import com.pims.api.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;


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

    private final UserInfoService userInfoService;

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
    public ResponseEntity<?> joinEmployee(@RequestBody @Valid final EmployeeJoinDto employeeJoinDto) throws NoSuchAlgorithmException {

        // email 존재 체크
        if (employeeService.existsByEmpId(employeeJoinDto.getEmpId())) {
            return responseUtils.getResponse(ResultCode.DUPLICATE_EMAIL);
        }

        // HP 존재 체크
        if (employeeService.existsByEmpHp(employeeJoinDto.getEmpHp())) {
            return responseUtils.getResponse(ResultCode.DUPLICATE_HP);
        }

        // 직급, 팀, 개발자 포지션 정보 코드 유효성 체크
        if (!userInfoService.existsByRankCd(employeeJoinDto.getRankCd()) ||
            !userInfoService.existsByTeamCd(employeeJoinDto.getTeamCd()) ||
            !userInfoService.existsByDevPosCd(employeeJoinDto.getDevPosCd())) {
            return responseUtils.getResponse(ResultCode.NON_EXISTENT);
        }

        // TODO 이메일 인증 후 회원가입.

        // 비밀번호 암호화
        employeeJoinDto.setEmpPwd(EncryptUtil.makeSHA256(employeeJoinDto.getEmpPwd()));

        // 회원 등록
        if (!employeeService.joinEmployee(employeeJoinDto)) {
            return responseUtils.getResponse(ResultCode.FAILURE);
        }
        return responseUtils.getSuccess(ResultCode.SUCCESS);
    }

    /**
     * Controller
     * : 사용자 로그인 API
     *
     * @authLevel 1
     * @method POST
     * @uriPath /user/employee/login
     *
     * @param   employeeLoginDto 로그인 DTO
     * @return  org.springframework.http.ResponseEntity<?>
     */
    @RequestMapping(value = "/employee/login", method = RequestMethod.POST)
    public ResponseEntity<?> loginEmployee(@RequestBody @Valid final EmployeeLoginDto employeeLoginDto) throws NoSuchAlgorithmException {

        // email 존재 체크
        if (!employeeService.existsByEmpId(employeeLoginDto.getEmpId())) {
            return responseUtils.getResponse(ResultCode.NON_EXISTENT_EMAIL_ID);
        }

        employeeLoginDto.setEmpPwd(EncryptUtil.makeSHA256(employeeLoginDto.getEmpPwd()));

        return responseUtils.getSuccess(ResultCode.SUCCESS);
    }


}
