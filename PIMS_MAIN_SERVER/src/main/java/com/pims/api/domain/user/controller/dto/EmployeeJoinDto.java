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

    // TODO DB에서 캐싱 조회 후 유효성 검사 로직 추가
    private String rankCd;

    // TODO DB에서 캐싱 조회 후 유효성 검사 로직 추가
    private String teamCd;

    private String empHp;

    private String isDeveloper;

    // TODO DB에서 캐싱 조회 후 유효성 검사 로직 추가
    private String devPosCd;

    private Integer devMonth;

}
