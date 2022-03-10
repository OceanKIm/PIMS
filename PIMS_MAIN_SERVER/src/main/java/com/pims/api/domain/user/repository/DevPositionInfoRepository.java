package com.pims.api.domain.user.repository;

import com.pims.api.domain.user.entity.DevPositionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DevPositionInfoRepository
 * : 개발자 포지션 정보 레파지토리
 * 
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-10
**/
public interface DevPositionInfoRepository extends JpaRepository<DevPositionInfo, String> {

    boolean existsByDevPosCd(String devPosCd);

}