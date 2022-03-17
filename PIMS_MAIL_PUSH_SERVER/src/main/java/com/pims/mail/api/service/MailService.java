package com.pims.mail.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sendFrom;

    public boolean sendAuthMail(String userEmail) {

        // TODO: 2021-09-16 NCJ
        // 메일 내용구성 필요

        String mailTitle = "";
        String mailContents = "<p>안녕하세요.<p>";

        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setTo(userEmail);
            message.setFrom(sendFrom);    //env.getProperty("spring.mail.username")
            message.setSubject(mailTitle);
            message.setText(mailContents, true); //ture : html 형식 사용
        };

        try {
            mailSender.send(mimeMessagePreparator);
        } catch (MailException e) {
            log.error("============================================================================");
            log.error("error : {}" , e.getMessage());
            log.error("============================================================================");
            return false;
        }
        return true;
    }

}
