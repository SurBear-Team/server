package com.surbear.payment.model;

import lombok.Builder;

@Builder
public record PaymentHistory(
        Long id,
        Long memberId,
        String paymentItem,
        Integer usedPoint,
        boolean deleted

) {
}
