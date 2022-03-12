package com.pims.api.domain.manage.entity;

import javax.persistence.*;

@Entity
@Table(name = "issue_tb")
public class Issue {
    @EmbeddedId
    private IssueId id; // TODO EmbeddedId 구조 살펴보기.

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

    @Column(name = "reg_dt", nullable = false, length = 45)
    private String regDt;

    @Column(name = "mod_dt", nullable = false, length = 45)
    private String modDt;

    public String getModDt() {
        return modDt;
    }

    public void setModDt(String modDt) {
        this.modDt = modDt;
    }

    public String getRegDt() {
        return regDt;
    }

    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    public String getIssueEndDt() {
        return issueEndDt;
    }

    public void setIssueEndDt(String issueEndDt) {
        this.issueEndDt = issueEndDt;
    }

    public String getIssueStartDt() {
        return issueStartDt;
    }

    public void setIssueStartDt(String issueStartDt) {
        this.issueStartDt = issueStartDt;
    }

    public String getIssueDesc() {
        return issueDesc;
    }

    public void setIssueDesc(String issueDesc) {
        this.issueDesc = issueDesc;
    }

    public String getIssueTitle() {
        return issueTitle;
    }

    public void setIssueTitle(String issueTitle) {
        this.issueTitle = issueTitle;
    }

    public Integer getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(Integer projectNo) {
        this.projectNo = projectNo;
    }

    public Integer getEmpProjectRltnNo() {
        return empProjectRltnNo;
    }

    public void setEmpProjectRltnNo(Integer empProjectRltnNo) {
        this.empProjectRltnNo = empProjectRltnNo;
    }

    public IssueId getId() {
        return id;
    }

    public void setId(IssueId id) {
        this.id = id;
    }
}