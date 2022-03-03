package com.pims.api.domain.etc.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "pims_config")
public class ConfigEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "config_no", nullable = false)
    private Integer id;

    @Column(name = "pims_key", nullable = false, length = 50)
    private String pimsKey;

    @Column(name = "pims_value", length = 200)
    private String pimsValue;

    @Column(name = "pims_desc")
    private String pimsDesc;

}