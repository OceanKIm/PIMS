package com.pims.api.domain.manage.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "issue_tb")
public class Issue {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "issue_no", nullable = false)
    private Integer issueNo;

    @Column(name = "emp_project_rltn_no", nullable = false)
    private Integer empProjectRltnNo;

    @Column(name = "project_no", nullable = false)
    private Integer projectNo;

    @Column(name = "issue_title", nullable = false, length = 200)
    private String issueTitle;

    @Lob
    @Column(name = "issue_desc", nullable = false)
    private String issueDesc;

    @Column(name = "issue_start_dt", nullable = false, length = 45)
    private String issueStartDt;

    @Column(name = "issue_end_dt", nullable = false, length = 45)
    private String issueEndDt;

    @Column(name = "issue_st", nullable = false, length = 1)
    private String issueSt;

    @Column(name = "reg_dt", nullable = false, length = 45)
    private String regDt;

    @Column(name = "mod_dt", nullable = false, length = 45)
    private String modDt;

}