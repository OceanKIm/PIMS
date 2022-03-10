package com.pims.api.domain.user.service;

import com.pims.api.domain.user.repository.DevPositionInfoRepository;
import com.pims.api.domain.user.repository.RankInfoRepository;
import com.pims.api.domain.user.repository.TeamInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * UserInfoService
 * : 사용자 도메인 정보 테이블 total service
 * : RankInfo Service
 * : TeamInfo Service
 * : DevPosition Service
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-10
**/
@RequiredArgsConstructor
@Service
@Log4j2
public class UserInfoService {

    private final RankInfoRepository rankInfoRepository;

    private final TeamInfoRepository teamInfoRepository;

    private final DevPositionInfoRepository devPositionInfoRepository;

    /**
     * 직급 코드 유효성 체크 Service.
     *
     * @param rankCd 직급 코드
     * @return boolean
    */
    public boolean existsByRankCd(String rankCd) {
        return rankInfoRepository.existsByRankCd(rankCd);
    }

    /**
     * 팀 코드 유효성 체크 Service.
     *
     * @param teamCd 팀 코드
     * @return boolean
     */
    public boolean existsByTeamCd(String teamCd) {
        return teamInfoRepository.existsByTeamCd(teamCd);
    }

    /**
     * 개발자 포지션 코드 유효성 체크 Service.
     *
     * @param devPosCd 개발자 포지션 코드
     * @return boolean
     */
    public boolean existsByDevPosCd(String devPosCd) {
        return devPositionInfoRepository.existsByDevPosCd(devPosCd);
    }

}
