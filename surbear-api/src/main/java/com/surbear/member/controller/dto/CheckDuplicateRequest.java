package com.surbear.member.controller.dto;

public record CheckDuplicateRequest(
        String type,
        String value
) {
}
