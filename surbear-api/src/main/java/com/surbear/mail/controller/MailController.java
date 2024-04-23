package com.surbear.mail.controller;

import com.surbear.member.controller.dto.CertificationRequest;
import com.surbear.mail.service.EmailService;
import com.surbear.survey.dto.EmailRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@Tag(name = "메일", description = "메일 관련 API")
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {

    private final EmailService emailService;


    @Operation(summary = "인증번호 확인", description = "사용자가 전달하는 번호와 프론트에서 가지고있는 번호 일치 체크")
    @PostMapping("/certification")
    public void checkCertification(@RequestBody CertificationRequest req) {
        emailService.checkCertification(req);
    }

    @Operation(summary = "이메일 전송", description = "이메일을 받아 그곳으로 인증번호를 보내는 api 생성")
    @PostMapping()
    public CompletableFuture<String> sendMail(@RequestBody EmailRequest req) {
        return emailService.sendVerificationCode(req.email());
    }
}