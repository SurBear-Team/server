package com.surbear.payment.dto;

public record BuyingRequest(
        int price,
        String paymentItemName
) {
}
