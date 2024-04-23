package com.surbear.member.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record LoginResponse(
        @NotEmpty
        String accessToken
) {
}
