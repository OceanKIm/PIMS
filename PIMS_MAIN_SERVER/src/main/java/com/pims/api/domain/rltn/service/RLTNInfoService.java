package com.pims.api.domain.rltn.service;

import com.pims.api.domain.rltn.repository.ProjectPosInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * RLTNInfoService
 * : 연관 도메인 정보 테이블 total service
 * : ProjectPosInfo Service
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-10
**/
@RequiredArgsConstructor
@Service
@Log4j2
public class RLTNInfoService {

    private final ProjectPosInfoRepository projectPosInfoRepository;

    /**
     * 프로젝트 포지션 코드 유효성 체크 Service.
     *
     * @param projectPosCd 개발자 포지션 코드
     * @return boolean
     */
    public boolean existsByProjectPosCd(String projectPosCd) {
        return projectPosInfoRepository.existsByProjectPosCd(projectPosCd);
    }

}
