package com.surbear.oauth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    private final CertificationNumberProvider provider;


    @Async
    public void sendMail(String email) {
        String tmpPassword = generateCode();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("인증번호 입니다.");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setText("인증번호: "+tmpPassword);

        javaMailSender.send(simpleMailMessage);
    }


    private String generateCode() {
        return provider.generateRandomCertificationNumber();
    }
}
