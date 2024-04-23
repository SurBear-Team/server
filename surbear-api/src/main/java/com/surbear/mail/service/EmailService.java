package com.surbear.mail.service;

import com.surbear.exception.ProcessErrorCodeException;
import com.surbear.exception.errorcode.BasicErrorCode;
import com.surbear.member.controller.dto.CertificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    private final CertificationNumberProvider provider;


    public CompletableFuture<String> sendVerificationCode(String email) {
        String certificationCode = generateCode();
        CompletableFuture.runAsync(() -> sendMail(email, certificationCode));
        return CompletableFuture.completedFuture(certificationCode);
    }


    @Async
    public void sendMail(String email, String certificationCode) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("인증번호 입니다.");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setText("인증번호: " + certificationCode);

        javaMailSender.send(simpleMailMessage);
    }


    private String generateCode() {
        return provider.generateRandomCertificationNumber();
    }

    public void checkCertification(CertificationRequest req) {
        if (!req.userCertification().equals(req.serverCertification())) {
            throw new ProcessErrorCodeException(BasicErrorCode.AUTHENTICATION_FAILURE_ERROR);
        }
    }
}
