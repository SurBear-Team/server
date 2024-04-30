package com.surbear.survey.dto.deletion;

import lombok.Builder;

import java.time.Instant;

@Builder
public record GetDeletionHistory(
        Instant createdAt,
        String title,
        String nickname
) {
}
