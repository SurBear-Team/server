package com.surbear.history.point.controller.dto;

import com.surbear.history.point.constant.PaymentType;
import lombok.Builder;

import java.time.Instant;

@Builder
public record PointHistoryAdminPage(
        Long id,
        String payer,
        String recipient,
        String description,
        Integer paidPoint,
        PaymentType paymentType,
        Instant updatedAt,
        boolean deleted

) {
}
