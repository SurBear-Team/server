package com.surbear.payment.model;

import lombok.Builder;

import java.time.Instant;

@Builder
public record PaymentHistory(
        Long id,
        Long memberId,
        String paymentItem,
        Integer usedPoint,
        Instant updatedAt,
        boolean deleted

) {
}
