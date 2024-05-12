package com.surbear.history.point.model;


import com.surbear.history.point.constant.PaymentType;
import lombok.Builder;

import java.time.Instant;

@Builder
public record PointHistory(
        Long id,
        Long payerId,
        Long recipientId,
        String description,
        Integer paidPoint,
        PaymentType paymentType,
        Instant updatedAt,
        boolean deleted
) {
}
