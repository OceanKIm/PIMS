package com.pims.api.domain.user.repository;

import com.pims.api.domain.user.entity.RankInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RankInfoRepository
 * : 직급 정보 레파지토리.
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-10
**/
public interface RankInfoRepository extends JpaRepository<RankInfo, String> {

    boolean existsByRankCd(String rankCd);

}