package com.pims.api.domain.etc.repository;

import com.pims.api.domain.etc.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConfigRepository extends JpaRepository<Config, Integer> {
}