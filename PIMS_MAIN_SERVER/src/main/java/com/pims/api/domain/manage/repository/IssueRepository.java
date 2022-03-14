package com.pims.api.domain.manage.repository;

import com.pims.api.domain.manage.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * IssueRepository
 * : 이슈 테이블 레파지토라
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-14
**/
public interface IssueRepository extends JpaRepository<Issue, Integer>, JpaSpecificationExecutor<Issue> {
}