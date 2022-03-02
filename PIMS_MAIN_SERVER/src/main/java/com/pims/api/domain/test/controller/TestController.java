package com.pims.api.domain.test.controller;



import com.pims.api.domain.test.service.TestService;

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
        testService.insertTest(hashMap);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}