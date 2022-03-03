package com.pims.api.domain.user.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "employee_tb")
public class EmployeeEntity {

    @Id
    @Column(name = "emp_no", nullable = false)
    private Integer id;

    @Column(name = "emp_id", nullable = false, length = 45)
    private String empId;

    @Column(name = "emp_pwd", nullable = false, length = 200)
    private String empPwd;

    @Column(name = "sys_auth_level", nullable = false)
    private Integer sysAuthLevel;

    @Column(name = "emp_nm", nullable = false, length = 10)
    private String empNm;

    @Column(name = "rank_cd", nullable = false)
    private Integer rankCd;

    @Column(name = "team_cd", nullable = false)
    private Integer teamCd;

    @Column(name = "emp_hp", length = 11)
    private String empHp;

    @Column(name = "is_developer", length = 2)
    private String isDeveloper;

    @Column(name = "dev_pos_cd")
    private Integer devPosCd;

    @Column(name = "dev_month")
    private Integer devMonth;

    @Column(name = "emp_st", nullable = false, length = 2)
    private String empSt;

    @Column(name = "reg_dt", nullable = false, length = 45)
    private String regDt;

    @Column(name = "mod_dt", nullable = false, length = 45)
    private String modDt;

}