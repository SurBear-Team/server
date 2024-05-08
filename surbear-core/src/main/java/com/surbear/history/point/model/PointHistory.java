package com.surbear.history.point.model;


import lombok.Builder;

@Builder
public record PointHistory(
        Long id,
        Long payerId,
        Long recipientId,
        String description,
        Integer paidPoint,
        boolean deleted
) {
}
