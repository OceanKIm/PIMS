package com.pims.api.domain.user.entity;

import lombok.*;

import javax.persistence.*;

/**
 * TeamInfo
 * : TeamInfo 테이블 entity
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-10
**/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "team_info")
public class TeamInfo {
    @Id
    @Column(name = "team_cd", nullable = false, length = 10)
    private String teamCd;

    @Column(name = "team_nm", nullable = false, length = 20)
    private String teamNm;

    @Lob
    @Column(name = "team_desc")
    private String teamDesc;

}