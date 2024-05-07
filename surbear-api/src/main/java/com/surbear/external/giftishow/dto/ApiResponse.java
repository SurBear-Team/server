package com.surbear.external.giftishow.dto;

public record ApiResponse(
        String code,
        String message,
        Result result
) {
}
