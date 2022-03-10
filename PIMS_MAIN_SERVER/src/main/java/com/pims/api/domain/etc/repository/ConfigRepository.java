package com.pims.api.domain.etc.repository;

import com.pims.api.domain.etc.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ConfigRepository
 * : 설정 JPA 레파지토리
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-08
**/
public interface ConfigRepository extends JpaRepository<Config, Integer> {
}