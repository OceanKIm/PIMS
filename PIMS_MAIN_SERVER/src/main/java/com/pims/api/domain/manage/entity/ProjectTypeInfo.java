package com.pims.api.domain.manage.entity;

import lombok.*;

import javax.persistence.*;

/**
 * ProjectTypeInfo
 * : ProjectTypeInfo 테이블 entity
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-16
**/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "project_type_info")
public class ProjectTypeInfo {

    @Id
    @Column(name = "project_type_cd", nullable = false, length = 10)
    private String projectTypeCd;

    @Column(name = "project_type_nm", length = 45)
    private String projectTypeNm;

    @Lob
    @Column(name = "project_type_desc")
    private String projectTypeDesc;

}