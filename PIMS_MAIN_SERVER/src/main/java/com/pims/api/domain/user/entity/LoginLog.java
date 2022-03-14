package com.pims.api.domain.user.entity;

import com.pims.api.utils.DateUtils;
import lombok.*;

import javax.persistence.*;

/**
 * LoginLog
 * : LoginLog 테이블 entity
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-14
**/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "login_log")
public class LoginLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_log_no", nullable = false)
    private Integer loginLogNo;

    @Column(name = "emp_no", nullable = false)
    private Integer empNo;

    @Column(name = "login_ip", nullable = false, length = 200)
    private String loginIp;

    @Column(name = "login_dt", nullable = false, length = 45)
    private String loginDt = DateUtils.getCurrentDay(DateUtils.DATE_TIME_PATTERN); // default 현재 날짜

}