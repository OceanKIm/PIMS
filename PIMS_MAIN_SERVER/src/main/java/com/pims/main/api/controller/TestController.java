package com.pims.main.api.controller;


import com.pims.main.api.mapper.TestMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@Log4j2
@RestController
public class TestController {

    @Autowired
    private TestMapper testMapper;

    @RequestMapping(value = "test.do", method = RequestMethod.POST)
    public ResponseEntity<?> testApi(@RequestBody HashMap<String, Object> hashMap) {
        System.out.println("=========== api test ===========");
        System.out.println(hashMap);
        testMapper.insertTest(hashMap);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}