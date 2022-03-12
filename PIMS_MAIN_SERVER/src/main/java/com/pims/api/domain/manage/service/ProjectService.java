package com.pims.api.domain.manage.service;

import com.pims.api.cont.ResultCode;
import com.pims.api.custom.CustomModelMapper;
import com.pims.api.domain.manage.controller.dto.ProjectCreateDto;
import com.pims.api.domain.manage.entity.Project;
import com.pims.api.domain.manage.repository.ProjectRepository;
import com.pims.api.exception.CustomResponseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ProjectService
 * : 프로젝트 관리 서비스
 * 
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-10
**/
@RequiredArgsConstructor
@Service
@Log4j2
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final CustomModelMapper customModelMapper;

    /**
     * 프로젝트 생성 service
     *
     * @param projectCreateDto 프로젝트 생성 DTO
     * @return  boolean
    */
    public boolean createProject(ProjectCreateDto projectCreateDto) {
        Project project = customModelMapper.strictMapper().map(projectCreateDto, Project.class);
        project = projectRepository.save(project);
        if (null == project) {
            return false;
        }
        return true;
    }
    
    /**
     * 프로젝트 정보 리스트 조회 service
     * 
     * @return List<Project> 프로젝트 정보 리스트
    */
    public List<Project> selectProjectInfoList() {
        return projectRepository.findAll();
    }

    /**
     * 프로젝트 정보 단건 조회 service
     *
     * @param projectCd 프로젝트 코드
     * @return Project 프로젝트 단건 정보
     */
    public Project selectProjectInfoDetail(String projectCd) {
        Optional<Project> optional = projectRepository.findByProjectCd(projectCd);
        return optional.orElseThrow(() -> new CustomResponseException(ResultCode.NON_EXISTENT));
    }

}
