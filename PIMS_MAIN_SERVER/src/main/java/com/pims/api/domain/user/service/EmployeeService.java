package com.pims.api.domain.user.service;

import com.pims.api.custom.CustomModelMapper;
import com.pims.api.domain.user.controller.dto.EmployeeJoinDto;
import com.pims.api.domain.user.entity.Employee;
import com.pims.api.domain.user.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

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

}
