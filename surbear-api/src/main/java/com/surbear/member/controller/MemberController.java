package com.surbear.member.controller;


import com.surbear.member.controller.dto.LoginRequest;
import com.surbear.member.controller.dto.LoginResponse;
import com.surbear.member.model.Member;
import com.surbear.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "회원", description = "회원 관련 API")
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입", description = "유저정보를 전달받아 회원가입을 수행한다")
    @PostMapping("/signup")
    public Long signUp(@RequestBody Member member) {
        return memberService.create(member);
    }

    @Operation(summary = "로그인", description = "아이디와 비밀번호를 입력받아 로그인을 진행하고 accessToken을 제공한다")
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        return memberService.login(req);
    }

}
