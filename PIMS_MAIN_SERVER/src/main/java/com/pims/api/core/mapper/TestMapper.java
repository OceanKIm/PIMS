package com.pims.api.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;


@Repository
@Mapper
public interface TestMapper {

    Integer insertTest(HashMap<String, Object> requestMap);

}