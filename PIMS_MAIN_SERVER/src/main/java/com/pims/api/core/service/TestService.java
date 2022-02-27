package com.pims.api.core.service;

import com.pims.api.core.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public Integer insertTest(HashMap<String, Object> requestMap) {
        //return null;
       return testMapper.insertTest(requestMap);
    }
}
