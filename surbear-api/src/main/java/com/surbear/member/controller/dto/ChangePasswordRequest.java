package com.surbear.member.controller.dto;

import lombok.Builder;

@Builder
public record ChangePasswordRequest(
        String email,
        String newPassword
) {
}
