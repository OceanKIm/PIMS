package com.pims.mail.api.controller;


import com.pims.mail.api.service.MailService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonTest {

    @Autowired
    private MailService mailService;

    public void setUp() {
    }

    @Test
    public void test() {
        mailService.sendAuthMail("gotmd37@naver.com");
        mailService.sendAuthMail("hskim@ocube.co.kr");
    }


}


