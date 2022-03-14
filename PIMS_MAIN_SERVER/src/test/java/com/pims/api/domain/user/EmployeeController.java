package com.pims.api.domain.user;

import com.pims.api.cont.ResultCode;
import com.pims.api.domain.user.controller.dto.EmployeeLoginDto;
import com.pims.api.domain.user.repository.EmployeeRepository;
import com.pims.api.domain.user.service.UserInfoService;
import com.pims.api.exception.CustomResponseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeController {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    EmployeeRepository employeeRepository;

    public void setUp() {
    }

    @Test
    public void test() throws Exception {
        Optional<EmployeeLoginDto> optional = employeeRepository.findByEmpId("gotmd37@naver.com2");
        System.out.println(optional.orElseThrow(() -> new CustomResponseException(ResultCode.NON_EXISTENT)));
    }
}


