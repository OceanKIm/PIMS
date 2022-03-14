package com.pims.api.domain.manage.controller.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProjectCreateDto implements Serializable {

    @NotEmpty
    private String projectCd;

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
