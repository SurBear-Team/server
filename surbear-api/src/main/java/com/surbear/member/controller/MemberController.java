package com.surbear.member.controller;


import com.surbear.member.controller.dto.ChangePasswordRequest;
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
        return memberService.signUp(member);
    }

    @Operation(summary = "로그인", description = "아이디와 비밀번호를 입력받아 로그인을 진행하고 accessToken을 제공한다")
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        return memberService.login(req);
    }

    @Operation(summary = "아이디 찾기", description = "제공한 이메일으로 아이디를 찾는다")
    @GetMapping("/userId")
    public String findUserId(@RequestParam String email) {
        return memberService.findUserIdByEmail(email);
    }

    @Operation(summary = "비밀번호 재설정", description = "이메일을 기반으로 검색하여 비밀번호를 재설정한다")
    @PutMapping("/password")
    public boolean changePassword(@RequestBody ChangePasswordRequest req) {
        return memberService.changePassword(req.email(), req.newPassword());
    }

    @Operation(summary = "회원 탈퇴", description = "회원id를 기반으로 탈퇴")
    @DeleteMapping("{memberId}")
    public boolean delete(@PathVariable Long memberId) {
        return memberService.delete(memberId);
    }

}
