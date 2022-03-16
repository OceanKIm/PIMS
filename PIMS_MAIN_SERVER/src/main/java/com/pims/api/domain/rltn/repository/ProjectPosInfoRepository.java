package com.pims.api.domain.rltn.repository;

import com.pims.api.domain.rltn.entity.ProjectPosInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * ProjectPosInfoRepository
 * : 프로젝트 포지션 정보 레파지토리
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-16
**/
public interface ProjectPosInfoRepository extends JpaRepository<ProjectPosInfo, String>, JpaSpecificationExecutor<ProjectPosInfo> {

    boolean existsByProjectPosCd(String projectPosCd);

}