package com.pims.api.domain.manage.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class IssueId implements Serializable {
    private static final long serialVersionUID = -1763907928862282934L;
    @Column(name = "issue_no", nullable = false)
    private Integer issueNo;
    @Column(name = "issue_st", nullable = false, length = 1)
    private String issueSt;

    public String getIssueSt() {
        return issueSt;
    }

    public void setIssueSt(String issueSt) {
        this.issueSt = issueSt;
    }

    public Integer getIssueNo() {
        return issueNo;
    }

    public void setIssueNo(Integer issueNo) {
        this.issueNo = issueNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(issueNo, issueSt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        IssueId entity = (IssueId) o;
        return Objects.equals(this.issueNo, entity.issueNo) &&
                Objects.equals(this.issueSt, entity.issueSt);
    }
}