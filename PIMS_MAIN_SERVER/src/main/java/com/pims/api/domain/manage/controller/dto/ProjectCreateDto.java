package com.pims.api.domain.manage.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCreateDto implements Serializable {

    @NotEmpty
    private String projectType;

    @NotEmpty
    private String projectTitle;

    @NotEmpty
    private String projectDesc;

    // TODO 날짜 유효성 체크
    @NotEmpty
    private String projectStartDt;

    // TODO 날짜 유효성 체크
    @NotEmpty
    private String projectEndDt;
}
