package com.pims.api.domain.user.controller.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * EmployeeJoinDto
 * : 사원 회원가입 DTO
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-08
**/
@Data
public class EmployeeJoinDto implements Serializable {

    @NotEmpty
    @Email
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
