package com.pims.api.domain.user.repository;

import com.pims.api.domain.user.entity.TeamInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TeamInfoRepository
 * : 팀 정보 레파지토리.
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-10
**/
public interface TeamInfoRepository extends JpaRepository<TeamInfo, String> {

    boolean existsByTeamCd(String teamCd);

}