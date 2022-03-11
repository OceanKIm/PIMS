package com.pims.api.domain.manage.repository;

import com.pims.api.domain.manage.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * ProjectRepository
 * : 프로젝트 테이블 레파지토리
 * 
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-10
**/
public interface ProjectRepository extends JpaRepository<Project, Integer>, JpaSpecificationExecutor<Project> {
}