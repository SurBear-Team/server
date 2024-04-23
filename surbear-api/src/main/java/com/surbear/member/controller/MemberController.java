package com.surbear.member.controller;


import com.surbear.member.service.MemberService;
import com.surbear.oauth.service.CertificationNumberProvider;
import com.surbear.oauth.service.EmailService;
import com.surbear.survey.dto.EmailRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "회원", description = "회원 관련 API")
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final CertificationNumberProvider provider;
    private final MemberService service;
    private final EmailService emailService;


    @Operation(summary = "임시비밀번호 테스트 api", description = "")
    @GetMapping("")
    public String testPassword() {
        return provider.generateRandomCertificationNumber();
    }

    @Operation(summary = "이메일 전송", description = "")
    @PostMapping("")
    public ResponseEntity<Void> sendMail(@RequestBody EmailRequest req) {
        emailService.sendMail(req.email());
        return ResponseEntity.ok().build();
    }
}
