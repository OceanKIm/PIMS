package com.pims.api.domain.etc.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "pims_config")
@Builder
public class Config {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer config_no;

    @Column(nullable = false)
    private String pims_key;

    private String pims_value;

    private String pims_desc;

}
