package com.pims.api.domain.user.controller;

import com.pims.api.cont.Const;
import com.pims.api.cont.ResultCode;
import com.pims.api.custom.CustomMap;
import com.pims.api.domain.common.dto.TokenDTO;
import com.pims.api.domain.user.controller.dto.EmployeeJoinDto;
import com.pims.api.domain.user.controller.dto.EmployeeLoginDto;
import com.pims.api.domain.user.entity.LoginLog;
import com.pims.api.domain.user.service.EmployeeService;
import com.pims.api.domain.user.service.LoginLogService;
import com.pims.api.domain.user.service.UserInfoService;
import com.pims.api.exception.CustomForbiddenException;
import com.pims.api.provider.JwtProvider;
import com.pims.api.utils.EncryptUtil;
import com.pims.api.utils.ResponseUtils;
import com.pims.api.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;


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

    private final LoginLogService loginLogService;

    private final JwtProvider jwtProvider;

    /**
     * Controller
     * : 사용자 회원 가입 API
     *
     * @authLevel ALL
     * @method  POST
     * @uriPath /user/employee/join.do
     *
     * @param   employeeJoinDto 회원가입 dto
     * @return  org.springframework.http.ResponseEntity<?>
     */
    @RequestMapping(value = "/employee/join.do", method = RequestMethod.POST)
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
     * @authLevel ALL
     * @method POST
     * @uriPath /user/employee/login.do
     *
     * @param   employeeLoginDto 로그인 DTO
     * @return  org.springframework.http.ResponseEntity<?>
     */
    @RequestMapping(value = "/employee/login.do", method = RequestMethod.POST)
    public ResponseEntity<?> loginEmployee(HttpServletRequest request, @RequestBody @Valid final EmployeeLoginDto employeeLoginDto) throws NoSuchAlgorithmException {

        HashMap<String, Object> responseMap = new HashMap<>();

        // 로그인 인증 처리
        if (!employeeService.loginEmployee(employeeLoginDto)) {
            return  responseUtils.getResponse(ResultCode.NOT_EQUAL_PASSWORD);
        }

        // 로그인 로깅 처리
        String address = Utils.getIpAddress(request);
        LoginLog loginLog = loginLogService.insertLog(employeeLoginDto.getEmpNo(), address);

        // jwt 토큰 생성
        TokenDTO tokenDTO = jwtProvider.generateTokenDto(Const.USER_ROLE.getUserRole(employeeLoginDto.getEmpRole()), employeeLoginDto.getEmpNo());

        // 토큰 정보 response 등록
        responseMap.put("tokenInfo", tokenDTO);

        // 로그인 정보 response 등록
        responseMap.put("loginInfo", new CustomMap().put("empNo", loginLog.getEmpNo())
                                                    .put("loginIp", loginLog.getLoginIp())
                                                    .put("loginDt", loginLog.getLoginDt()));

        return responseUtils.getSuccess(responseMap);
    }

    /**
     * Controller
     * : JWT 토큰 리프레쉬 API
     *
     * @authLevel ALL
     * @method  GET or POST or PUT or DELETE
     * @uriPath api uri 경로를 입력한다.
     *
     * @Header
     * @return  org.springframework.http.ResponseEntity<?>
     */
    @RequestMapping(value = "/employee/refreshToken.do", method = RequestMethod.POST)
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        HashMap<String,Object> responseMap = new HashMap<>();
        String token = jwtProvider.resolveRefreshToken(request);
        if (null != token && jwtProvider.isValidateToken(token)) {

            // refresh 토큰인지 유효성 검사
            String type = jwtProvider.getTokenPayLoadInfo(token, Const.JWT_KEY.type.name());
            if (!Const.JWT_KEY.REFRESH_TOKEN.equals(type)) {
                throw new CustomForbiddenException(ResultCode.NOT_TOKEN_TYPE_ERROR);
            }

            // refresh token 정보 가져오기
            CustomMap bodyMap = jwtProvider.getTokenBodyInfo(token);

            // jwt 토큰 생성
            TokenDTO tokenDTO = jwtProvider.generateTokenDto(
                    Const.USER_ROLE.getUserRole((int)bodyMap.get(Const.JWT_KEY.level.name())),
                    (int)bodyMap.get(Const.JWT_KEY.empNo.name()));

            responseMap.put("tokenInfo", tokenDTO);
        } else {
            return responseUtils.getResponse(ResultCode.FAILURE);
        }
        return responseUtils.getSuccess(responseMap);
    }

}
