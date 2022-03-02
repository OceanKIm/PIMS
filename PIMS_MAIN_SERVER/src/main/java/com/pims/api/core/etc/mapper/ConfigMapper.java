package com.pims.api.core.etc.mapper;

import com.pims.api.custom.CustomMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * ConfigMapper
 * : 설정 관련 맵퍼
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-02
**/
@Repository
@Mapper
public interface ConfigMapper {

    ArrayList<CustomMap> selectConfigInfoList();

    CustomMap selectConfigInfo(String key);

    Integer updateConfigInfo(@Param("requestArray") ArrayList<CustomMap> requestArray);
}
