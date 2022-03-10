package com.pims.api.domain.user.repository;

import com.pims.api.domain.user.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * EmployeeRepository
 * : 사원 JPA 레파지토리
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-08
**/
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer>, JpaSpecificationExecutor<EmployeeEntity> {

    boolean existsByEmpId(String EmpId);

}