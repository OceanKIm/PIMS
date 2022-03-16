package com.pims.api.domain.rltn.entity;

import lombok.*;

import javax.persistence.*;

/**
 * ProjectPosInfo
 * : ProjectPosInfo 테이블 entity
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
@Table(name = "project_pos_info")
public class ProjectPosInfo {

    @Id
    @Column(name = "project_pos_cd", nullable = false, length = 10)
    private String projectPosCd;

    @Column(name = "project_pos_nm", length = 45)
    private String projectPosNm;

    @Lob
    @Column(name = "project_pos_desc")
    private String projectPosDesc;

}