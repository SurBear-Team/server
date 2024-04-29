package com.surbear.member.controller;


import com.surbear.configuration.authorization.Authorization;
import com.surbear.member.controller.dto.ChangePasswordRequest;
import com.surbear.member.controller.dto.LoginRequest;
import com.surbear.member.controller.dto.LoginResponse;
import com.surbear.member.model.Member;
import com.surbear.member.service.FacadeMemberService;
import com.surbear.member.service.MemberService;
import com.surbear.survey.dto.survey.history.ParticipatedSurvey;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "회원", description = "회원 관련 API")
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final FacadeMemberService facadeMemberService;

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

    @Operation(summary = "회원 정보 조회(사용자용)", description = "토큰 기반 회원정보 조회")
    @GetMapping("")
    public Member getMemberInfoByToken(
            @Authorization
            @Parameter(hidden = true)
            Long memberId) {
        return memberService.getMemberInfoByToken(memberId);
    }

    @Operation(summary = "유저 참여 설문 기록 조회(사용자용)", description = "유저의 아이디를 기반으로 참여한 설문 기록을 조회한다")
    @GetMapping("/survey/history")
    public List<ParticipatedSurvey> getParticipatedSurveyList(
            @Authorization
            @Parameter(hidden = true)
            Long memberId) {
        return facadeMemberService.getParticipatedSurveyList(memberId);
    }
}
