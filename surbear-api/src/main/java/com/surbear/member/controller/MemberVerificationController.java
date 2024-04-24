package com.surbear.member.controller;

import com.surbear.member.controller.dto.CheckDuplicateRequest;
import com.surbear.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@Tag(name = "회원 검증", description = "회원 검증 관련 API")
@RequiredArgsConstructor
@RequestMapping("/member/verification")
public class MemberVerificationController {

    private final MemberService memberService;

    @Operation(summary = "아이디 찾기에서 존재하는 이메일인지 확인", description = "존재하는 회원인경우 true반환")
    @GetMapping("/email")
    public boolean checkExistsEmail(@RequestParam String email) {
        return memberService.checkEmailExists(email);
    }


    @Operation(summary = "비밀번호 찾기에서 유저가 존재하는지 확인", description = "존재하는 회원인경우 true반환")
    @GetMapping("/password")
    public boolean checkExistsEmailAndUserId(@RequestParam String userId, @RequestParam String email) {
        return memberService.checkUserIdAndEmailExists(userId, email);
    }

    @Operation(summary = "통합(이메일, 유저아이디, 닉네임) 중복 검사", description = "")
    @PostMapping("/duplicate")
    public boolean checkDuplicateEmail(@RequestBody CheckDuplicateRequest req) {
        return memberService.checkDuplicate(req.type(),req.value());
    }
}
