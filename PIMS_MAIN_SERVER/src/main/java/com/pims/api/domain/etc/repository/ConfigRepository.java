package com.pims.api.domain.etc.repository;

import com.pims.api.domain.etc.entity.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigRepository extends JpaRepository<ConfigEntity, Integer> {
}