package com.surbear.member.controller;


import com.surbear.member.controller.dto.ChangePasswordRequest;
import com.surbear.member.controller.dto.LoginRequest;
import com.surbear.member.controller.dto.LoginResponse;
import com.surbear.member.model.Member;
import com.surbear.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/password")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordRequest req) {
        memberService.changePassword(req.email(),req.newPassword());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "아이디 찾기에서 존재하는 이메일인지 확인", description = "이메일이 존재하는 회원의 이메일인지 확인")
    @GetMapping("/certification/email")
    public ResponseEntity<Void> checkExistsEmail(@RequestParam String email) {
        memberService.checkEmailExists(email);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "비밀번호 찾기에서 유저가 존재하는지 확인", description = "userId와 email을 통해 조회해서 유저가 있는지 확인ㄴㄴ")
    @GetMapping("/certification/password")
    public ResponseEntity<Void> checkExistsEmailAndUserId(@RequestParam String userId, @RequestParam String email) {
        memberService.checkUserIdAndEmailExists(userId, email);
        return ResponseEntity.ok().build();
    }

}
