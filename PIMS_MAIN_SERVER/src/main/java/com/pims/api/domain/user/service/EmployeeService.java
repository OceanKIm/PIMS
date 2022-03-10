package com.pims.api.domain.user.service;

import com.pims.api.custom.CustomModelMapper;
import com.pims.api.domain.user.controller.dto.EmployeeJoinDto;
import com.pims.api.domain.user.entity.EmployeeEntity;
import com.pims.api.domain.user.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * 회원 등록 Service
     *
     * @param employeeJoinDto 회원가입 DTO
     * @return  EmployeeEntity
    */
    public EmployeeEntity joinEmployee(EmployeeJoinDto employeeJoinDto) {
        EmployeeEntity employeeEntity = customModelMapper.strictMapper().map(employeeJoinDto, EmployeeEntity.class);
        return employeeRepository.save(employeeEntity);
    }

}
