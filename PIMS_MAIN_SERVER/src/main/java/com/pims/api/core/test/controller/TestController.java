package com.pims.api.core.test.controller;



import com.pims.api.core.test.service.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;



@RestController
public class
TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "test.do", method = RequestMethod.POST)
    public ResponseEntity<?> testApi(@RequestBody HashMap<String, Object> hashMap) {
        System.out.println("=========== api test ===========");
        System.out.println(hashMap);
        testService.insertTest(hashMap);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}