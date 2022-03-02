package com.pims.api;

import com.pims.api.cont.Const;
import com.pims.api.domain.etc.service.ConfigService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

import javax.annotation.PreDestroy;
import java.net.InetAddress;


/**
 * MainApplication
 * : 메인 서버 스타트 클래스
 * 
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-02-23
**/
@Log4j2
@SpringBootApplication
public class MainApplication {

	@Value("${server.port}")
	private Integer mPort;

	@Autowired
	private ConfigService configService;

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	/**
	 * 서버 시작 완료시 콜백
	 */
	@EventListener(ApplicationStartedEvent.class)
	public void startServer() {
		// DB 설정 정보 불러오기
		configService.loadServerConfig();
		String ip = InetAddress.getLoopbackAddress().getHostAddress();
		log.info("===============================================================");
		log.info("BASE SERVER CONFIG :");
		log.info("{}", Const.G_SERVER_CONFIG);
		log.info("===============================================================");
		log.info("SERVER RUNNING...");
		log.info("Address : {}", ip);
		log.info("Port : {}", mPort);
		log.info("===============================================================");
	}

	/**
	 * 서버 종료시 콜백
	 */
	@PreDestroy
	public void onExit() {

	}

}
