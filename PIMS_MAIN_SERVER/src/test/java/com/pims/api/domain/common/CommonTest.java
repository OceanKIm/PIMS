package com.pims.api.domain.common;

import com.pims.api.domain.user.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonTest {

    @Autowired
    UserInfoService userInfoService;

    public void setUp() {
    }

    @Test
    public void test() throws Exception {

        String rankCd = "R1";
        String teamCd = "DEV2";
        String devPosCd = "BE";

        System.out.println(userInfoService.existsByRankCd(rankCd));
        System.out.println(userInfoService.existsByTeamCd(teamCd));
        System.out.println(userInfoService.existsByDevPosCd(devPosCd));


    }


}


