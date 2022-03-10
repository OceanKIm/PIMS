package com.pims.api.domain.user.entity;

import com.pims.api.cont.Const;
import com.pims.api.utils.DateUtils;
import lombok.*;

import javax.persistence.*;

/**
 * EmployeeEntity
 * : employee_tb 테이블 entity
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
@Entity
@Table(name = "employee_tb")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // TODO modify
    @Column(name = "emp_no", nullable = false)
    private Integer empNo;

    @Column(name = "emp_id", nullable = false, length = 45)
    private String empId;

    @Column(name = "emp_pwd", nullable = false, length = 200)
    private String empPwd;

    @Column(name = "emp_role", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Const.USER_ROLE role = Const.USER_ROLE.user;

    @Column(name = "emp_nm", nullable = false, length = 10)
    private String empNm;

    @Column(name = "rank_cd", nullable = false)
    private String rankCd;

    @Column(name = "team_cd", nullable = false)
    private String teamCd;

    @Column(name = "emp_hp", length = 11)
    private String empHp;

    @Column(name = "is_developer", length = 2)
    private String isDeveloper;

    @Column(name = "dev_pos_cd")
    private String devPosCd;

    @Column(name = "dev_month")
    private Integer devMonth;

    @Column(name = "emp_st", length = 2)
    private String empSt = "W"; // default 가입 대기 상태

    @Column(name = "reg_dt", length = 45)
    private String regDt = DateUtils.getCurrentDay(DateUtils.DATE_TIME_PATTERN); // default 현재 날짜

    @Column(name = "mod_dt", length = 45)
    private String modDt = DateUtils.getCurrentDay(DateUtils.DATE_TIME_PATTERN); // default 현재 날짜

}