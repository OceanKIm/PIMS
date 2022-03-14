package com.pims.api.domain.user.controller.dto;

import com.pims.api.cont.Const;
import lombok.*;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeLoginDto implements Serializable {

    private Integer empNo;

    @NotEmpty
    @Email
    private String empId;

    @NotEmpty
    private String empPwd;

    private Const.USER_ROLE role;

}
