package com.pims.api.domain.user.entity;

import lombok.*;

import javax.persistence.*;

/**
 * DevPositionInfo
 * : DevPositionInfo 테이블 entity
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
@Table(name = "dev_position_info")
public class DevPositionInfo {

    @Id
    @Column(name = "dev_pos_cd", nullable = false, length = 10)
    private String devPosCd;

    @Column(name = "dev_pos_nm", nullable = false, length = 20)
    private String devPosNm;

    @Lob
    @Column(name = "dev_pos_desc")
    private String devPosDesc;

}