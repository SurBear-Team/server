package com.surbear.role.controller;


import com.surbear.role.controller.dto.AdminListResponse;
import com.surbear.role.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "권한", description = "권한 관련 API")
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;


    @Operation(summary = "관리자 등록", description = "닉네임을 입력받아 관리자를 추가한다.")
    @PostMapping("")
    public Long signUp(@RequestParam String nickname) {
        return roleService.adminRegistration(nickname);
    }

    @Operation(summary = "관리자 삭제", description = "기본키를 받아 관리자 권한을 삭제한다")
    @DeleteMapping("{id}")
    public boolean delete(@PathVariable Long id) {
        return roleService.deleteRegistration(id);
    }

    @Operation(summary = "관리자 리스트 조회", description = "관리자 리스트 조회")
    @GetMapping("/list")
    public List<AdminListResponse> getAdminList() {
        return roleService.getAdminList();
    }
}
