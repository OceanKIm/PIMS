package com.pims.api.domain.user.service;

import com.pims.api.cont.ResultCode;
import com.pims.api.custom.CustomModelMapper;
import com.pims.api.domain.user.controller.dto.EmployeeJoinDto;
import com.pims.api.domain.user.controller.dto.EmployeeLoginDto;
import com.pims.api.domain.user.entity.Employee;
import com.pims.api.domain.user.repository.EmployeeRepository;
import com.pims.api.exception.CustomResponseException;
import com.pims.api.utils.EncryptUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

/**
 * EmployeeService
 * : 사원 서비스
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-08
**/
@RequiredArgsConstructor
@Service
@Log4j2
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final CustomModelMapper customModelMapper;

    /**
     * 사원 Email 존재 유무 체크 Service.
     *
     * @param empId 사원 Email Id
     * @return boolean
    */
    public boolean existsByEmpId(String empId) {
        return employeeRepository.existsByEmpId(empId);
    }

    /**
     * 사원 HP 존재 유무 체크 Service.
     *
     * @param empHp 사원 HP
     * @return boolean
     */
    public boolean existsByEmpHp(String empHp) {
        return employeeRepository.existsByEmpHp(empHp);
    }


    /**
     * 회원 등록 Service
     *
     * @param employeeJoinDto 회원가입 DTO
     * @return  EmployeeEntity
    */
    public boolean joinEmployee(EmployeeJoinDto employeeJoinDto) {
        Employee employee = customModelMapper.strictMapper().map(employeeJoinDto, Employee.class);
        employee = employeeRepository.save(employee);
        if (null == employee) { // TODO save return 처리에 대해서 좀 더 알아 보기.
            return false;
        }
        return true;
    }

    /**
     * 로그인 인증 Service
     *
     * @param employeeLoginDto 로그인 DTO
     * @return  boolean 로그인 성공 유무
    */
    public boolean loginEmployee(EmployeeLoginDto employeeLoginDto) throws NoSuchAlgorithmException {

        // 사용자 정보 조회
        Optional<EmployeeLoginDto> optional = employeeRepository.findByEmpId(employeeLoginDto.getEmpId());
        EmployeeLoginDto savedLoginDTO = optional.orElseThrow(() -> new CustomResponseException(ResultCode.NON_EXISTENT_EMAIL_ID));

        // 비밀번호 암호화
        employeeLoginDto.setEmpPwd(EncryptUtil.makeSHA256(employeeLoginDto.getEmpPwd()));

        // 비밀번호 검증
        if(!employeeLoginDto.getEmpPwd().equals(savedLoginDTO.getEmpPwd())) {
            return false;
        }

        // 룰(권한) 정보 등록
        employeeLoginDto.setEmpRole(savedLoginDTO.getEmpRole());

        // 사원 일련번호 정보 등록
        employeeLoginDto.setEmpNo(savedLoginDTO.getEmpNo());

        return true;
    }

}
