package com.pims.api.domain.rltn.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * EmpProjectRLTN
 * : EmpProjectRLTN 테이블 entity
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-13
**/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "emp_project_rltn")
public class EmpProjectRLTN {

    @Id
    @Column(name = "emp_project_rltn_no", nullable = false)
    private Integer empProjectRLTNNo;

    @Column(name = "emp_no", nullable = false)
    private Integer empNo;

    @Column(name = "project_code", nullable = false)
    private Integer projectCode;

    @Column(name = "project_pos", nullable = false, length = 5)
    private String projectPos;

    @Column(name = "reg_dt", nullable = false, length = 45)
    private String regDt;

    @Column(name = "mod_dt", nullable = false, length = 45)
    private String modDt;

}