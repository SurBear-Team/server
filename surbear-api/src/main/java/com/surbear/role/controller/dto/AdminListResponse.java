package com.surbear.role.controller.dto;

import lombok.Builder;

@Builder
public record AdminListResponse(
        Long memberId,
        Long roleId,
        String nickname

) {
}
