package com.pims.api.domain.user.entity;

import lombok.*;

import javax.persistence.*;

/**
 * RankInfo
 * : RankInfo 테이블 entity
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
@Table(name = "rank_info")
public class RankInfo {

    @Id
    @Column(name = "rank_cd", nullable = false, length = 5)
    private String rankCd;

    @Column(name = "rank_nm", nullable = false, length = 20)
    private String rankNm;

    @Lob
    @Column(name = "rank_desc")
    private String rankDesc;

}