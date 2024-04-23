package com.surbear.member.controller.dto;

public record LoginRequest(
        String userId,
        String password
) {
}
