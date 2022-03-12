package com.pims.api.domain.rltn.repository;

import com.pims.api.domain.rltn.entity.EmpProjectRLTN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * EmpProjectRLTNRepository
 * : 사원 - 프로젝트 연관 테이블 레파지토리
 * 
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-13
**/
public interface EmpProjectRLTNRepository extends JpaRepository<EmpProjectRLTN, Integer>, JpaSpecificationExecutor<EmpProjectRLTN> {
}