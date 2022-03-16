package com.pims.api.domain.manage.repository;

import com.pims.api.domain.manage.entity.ProjectTypeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * ProjectTypeInfoRepository
 * : 프로젝트 타입 정보 테이블 레파지토리
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-16
**/
public interface ProjectTypeInfoRepository extends JpaRepository<ProjectTypeInfo, String>, JpaSpecificationExecutor<ProjectTypeInfo> {

    boolean existsByProjectTypeCd(String projectTypeCd);

}