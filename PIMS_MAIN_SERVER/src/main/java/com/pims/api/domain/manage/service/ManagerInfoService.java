package com.pims.api.domain.manage.service;

import com.pims.api.domain.manage.repository.ProjectTypeInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * ManagerInfoService
 * : 관리 도메인 정보 테이블 total service
 * : ProjectTypeInfo Service
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-10
 **/
@RequiredArgsConstructor
@Service
@Log4j2
public class ManagerInfoService {

    private final ProjectTypeInfoRepository projectTypeInfoRepository;

    /**
     * 프로젝트 타입 코드 유효성 체크 Service.
     *
     * @param projectTypeCd 개발자 포지션 코드
     * @return boolean
     */
    public boolean existsByProjectTypeCd(String projectTypeCd) {
        return projectTypeInfoRepository.existsByProjectTypeCd(projectTypeCd);
    }

}
