-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.6.4-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- pims 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `pims` /*!40100 DEFAULT CHARACTER SET utf8mb3 */;
USE `pims`;

-- 테이블 pims.activity_attach_file 구조 내보내기
CREATE TABLE IF NOT EXISTS `activity_attach_file` (
  `activity_file_no` int(11) NOT NULL,
  `actvity_no` int(11) NOT NULL,
  `activity_file_nm` varchar(100) NOT NULL,
  `activity_file_ext` varchar(5) NOT NULL,
  `activity_file_size` int(11) NOT NULL,
  `activity_save_path` varchar(200) NOT NULL,
  `activity_file_st` varchar(2) NOT NULL,
  `reg_dt` varchar(45) NOT NULL DEFAULT current_timestamp(),
  `mod_dt` varchar(45) NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`activity_file_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 pims.activity_mail_push_log 구조 내보내기
CREATE TABLE IF NOT EXISTS `activity_mail_push_log` (
  `mail_push_no` int(11) NOT NULL,
  `emp_no` int(11) NOT NULL,
  `actvity_no` int(11) NOT NULL,
  `is_mail_push` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`mail_push_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 pims.activity_tb 구조 내보내기
CREATE TABLE IF NOT EXISTS `activity_tb` (
  `actvity_no` int(11) NOT NULL,
  `issue_no` int(11) NOT NULL,
  `emp_project_rltn_no` int(11) NOT NULL,
  `activity_content` text NOT NULL,
  `reg_dt` varchar(45) NOT NULL DEFAULT current_timestamp(),
  `mod_dt` varchar(45) NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`actvity_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 pims.dev_postion_info 구조 내보내기
CREATE TABLE IF NOT EXISTS `dev_postion_info` (
  `dev_pos_cd` varchar(10) NOT NULL,
  `dev_pos_nm` varchar(20) NOT NULL,
  `dev_pos_desc` text DEFAULT NULL,
  PRIMARY KEY (`dev_pos_cd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 pims.employee_tb 구조 내보내기
CREATE TABLE IF NOT EXISTS `employee_tb` (
  `emp_no` int(11) NOT NULL,
  `emp_id` varchar(45) NOT NULL,
  `emp_pwd` varchar(200) NOT NULL,
  `emp_role` varchar(10) NOT NULL,
  `emp_nm` varchar(10) NOT NULL,
  `rank_cd` varchar(10) NOT NULL,
  `team_cd` varchar(10) NOT NULL,
  `emp_hp` varchar(11) DEFAULT NULL,
  `is_developer` varchar(2) DEFAULT NULL,
  `dev_pos_cd` varchar(10) DEFAULT NULL,
  `dev_month` int(11) DEFAULT NULL,
  `emp_st` varchar(2) NOT NULL,
  `reg_dt` varchar(45) NOT NULL DEFAULT current_timestamp(),
  `mod_dt` varchar(45) NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`emp_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 pims.emp_project_rltn 구조 내보내기
CREATE TABLE IF NOT EXISTS `emp_project_rltn` (
  `emp_project_rltn_no` int(11) NOT NULL,
  `emp_no` int(11) NOT NULL,
  `project_code` int(11) NOT NULL,
  `project_pos` varchar(5) NOT NULL,
  `reg_dt` varchar(45) NOT NULL DEFAULT current_timestamp(),
  `mod_dt` varchar(45) NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`emp_project_rltn_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 pims.hibernate_sequence 구조 내보내기
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 pims.issue_attach_file 구조 내보내기
CREATE TABLE IF NOT EXISTS `issue_attach_file` (
  `issue_file_no` int(11) NOT NULL,
  `issue_no` int(11) NOT NULL,
  `issue_file_nm` varchar(100) NOT NULL,
  `issue_file_ext` varchar(5) NOT NULL,
  `issue_file_size` int(11) NOT NULL,
  `issue_file_save_path` varchar(200) NOT NULL,
  `issue_file_st` varchar(2) NOT NULL,
  `reg_dt` varchar(45) NOT NULL DEFAULT current_timestamp(),
  `mod_dt` varchar(45) NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`issue_file_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 pims.login_log 구조 내보내기
CREATE TABLE IF NOT EXISTS `login_log` (
  `login_log_no` int(11) NOT NULL,
  `emp_no` int(11) NOT NULL,
  `login_web_token` varchar(200) NOT NULL,
  `login_dt` varchar(45) NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`login_log_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 pims.pims_config 구조 내보내기
CREATE TABLE IF NOT EXISTS `pims_config` (
  `config_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '설정 일련번호',
  `pims_key` varchar(50) NOT NULL COMMENT '설정 키',
  `pims_value` varchar(200) DEFAULT NULL COMMENT '설정 값',
  `pims_desc` varchar(255) DEFAULT NULL COMMENT '설정 설명',
  PRIMARY KEY (`config_no`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3 COMMENT='기본 설정 테이블';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 pims.project_attach_file 구조 내보내기
CREATE TABLE IF NOT EXISTS `project_attach_file` (
  `project_file_no` int(11) NOT NULL,
  `project_code` int(11) NOT NULL,
  `project_file_nm` varchar(100) NOT NULL,
  `project_file_ext` varchar(5) NOT NULL,
  `project_file_size` int(11) NOT NULL,
  `project_file_save_path` varchar(200) NOT NULL,
  `project_file_st` varchar(2) NOT NULL,
  `reg_dt` varchar(45) NOT NULL DEFAULT current_timestamp(),
  `mod_dt` varchar(45) NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`project_file_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 pims.project_issue_tb 구조 내보내기
CREATE TABLE IF NOT EXISTS `project_issue_tb` (
  `issue_no` int(11) NOT NULL,
  `emp_project_rltn_no` int(11) NOT NULL,
  `project_no` int(11) NOT NULL,
  `issue_title` varchar(200) NOT NULL,
  `issue_desc` text NOT NULL,
  `issue_start_dt` varchar(45) NOT NULL,
  `issue_end_dt` varchar(45) NOT NULL,
  `reg_dt` varchar(45) NOT NULL DEFAULT current_timestamp(),
  `mod_dt` varchar(45) NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`issue_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 pims.project_tb 구조 내보내기
CREATE TABLE IF NOT EXISTS `project_tb` (
  `project_code` int(11) NOT NULL,
  `project_type` varchar(5) NOT NULL,
  `project_title` varchar(200) NOT NULL,
  `project_desc` text NOT NULL,
  `project_start_dt` varchar(45) NOT NULL,
  `project_end_dt` varchar(45) NOT NULL,
  `project_st` varchar(2) NOT NULL,
  `reg_dt` varchar(45) NOT NULL DEFAULT current_timestamp(),
  `mod_dt` varchar(45) NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`project_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 pims.rank_info 구조 내보내기
CREATE TABLE IF NOT EXISTS `rank_info` (
  `rank_cd` varchar(5) NOT NULL,
  `rank_nm` varchar(20) NOT NULL,
  `rank_desc` text DEFAULT NULL,
  PRIMARY KEY (`rank_cd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 pims.team_info 구조 내보내기
CREATE TABLE IF NOT EXISTS `team_info` (
  `team_cd` varchar(10) NOT NULL,
  `team_nm` varchar(20) NOT NULL,
  `team_desc` text DEFAULT NULL,
  PRIMARY KEY (`team_cd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
