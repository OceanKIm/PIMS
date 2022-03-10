package com.pims.api.domain.user.controller.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * EmployeeLoginDto
 * : 사원 로그인 DTO
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-08
**/
@Data
public class EmployeeLoginDto implements Serializable {

    @NotEmpty
    @Email
    private String empId;

    @NotEmpty
    private String empPwd;

}
