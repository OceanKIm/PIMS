package com.pims.api.domain.user.repository;

import com.pims.api.domain.user.controller.dto.EmployeeLoginDto;
import com.pims.api.domain.user.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * EmployeeRepository
 * : 사원 JPA 레파지토리
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-08
**/
public interface EmployeeRepository extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {

    boolean existsByEmpId(String EmpId);

    boolean existsByEmpHp(String EmpHp);

    @Query("SELECT new com.pims.api.domain.user.controller.dto.EmployeeLoginDto(e.empNo, e.empId, e.empPwd, e.role) FROM Employee e WHERE e.empId = :empId")
    Optional<EmployeeLoginDto> findByEmpId(@Param("empId") String EmpId);

}