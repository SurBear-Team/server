package com.surbear.external.giftishow.dto;

public record ResponseRecord(
        String code,
        String message,
        GoodsDto result
) {
}
