package com.pims.api.domain.user.service;

import com.pims.api.custom.CustomModelMapper;
import com.pims.api.domain.user.controller.dto.EmployeeJoinDto;
import com.pims.api.domain.user.entity.EmployeeEntity;
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
     * 함수에 대한 설명을 작성한다.
     *
     * @param employeeJoinDto 회원가입 DTO
     * @return  EmployeeEntity
    */
    public EmployeeEntity joinEmployee(EmployeeJoinDto employeeJoinDto) {
        EmployeeEntity employeeEntity = customModelMapper.strictMapper().map(employeeJoinDto, EmployeeEntity.class);
        return employeeRepository.save(employeeEntity);
    }




}
