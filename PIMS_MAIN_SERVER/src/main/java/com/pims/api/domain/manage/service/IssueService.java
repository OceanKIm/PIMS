package com.pims.api.domain.manage.service;

import com.pims.api.custom.CustomModelMapper;
import com.pims.api.domain.manage.repository.IssueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * IssueService
 * : 이슈 관리 서비스.
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-14
**/
@RequiredArgsConstructor
@Service
@Log4j2
public class IssueService {

    private final IssueRepository issueRepository;

    private final CustomModelMapper customModelMapper;

}
