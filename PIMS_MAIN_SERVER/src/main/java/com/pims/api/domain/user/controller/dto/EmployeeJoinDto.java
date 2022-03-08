package com.pims.api.domain.user.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class EmployeeJoinDto implements Serializable {

    @NotEmpty
    private String empId;

    @NotEmpty
    private String empPwd;

    @NotEmpty
    private String empNm;

    private String rankCd;

    private String teamCd;

    private String empHp;

    private String isDeveloper;

    private String devPosCd;

    private Integer devMonth;

}
