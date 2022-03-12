package com.pims.api.domain.manage.entity;

import com.pims.api.utils.DateUtils;
import lombok.*;

import javax.persistence.*;

/**
 * Project
 * : Project 테이블 entity
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-10
**/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "project_tb")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // TODO modify
    @Column(name = "project_cd", nullable = false)
    private String projectCd;

    @Column(name = "project_type", nullable = false, length = 5)
    private String projectType;

    @Column(name = "project_title", nullable = false, length = 200)
    private String projectTitle;

    @Lob
    @Column(name = "project_desc", nullable = false)
    private String projectDesc;

    @Column(name = "project_start_dt", nullable = false, length = 45)
    private String projectStartDt;

    @Column(name = "project_end_dt", nullable = false, length = 45)
    private String projectEndDt;

    @Column(name = "project_st", nullable = false, length = 2)
    private String projectSt = "R"; // default 정상

    @Column(name = "reg_dt", nullable = false, length = 45)
    private String regDt = DateUtils.getCurrentDay(DateUtils.DATE_TIME_PATTERN); // default 현재 날짜

    @Column(name = "mod_dt", nullable = false, length = 45)
    private String modDt = DateUtils.getCurrentDay(DateUtils.DATE_TIME_PATTERN); // default 현재 날짜

}