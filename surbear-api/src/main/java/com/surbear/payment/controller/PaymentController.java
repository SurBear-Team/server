package com.surbear.payment.controller;


import com.surbear.common.authorization.Authorization;
import com.surbear.payment.dto.BuyingRequest;
import com.surbear.payment.model.PaymentHistory;
import com.surbear.payment.service.PaymentService;
import com.surbear.report.model.SurveyReport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "결제", description = "결제 관련 API")
@RequiredArgsConstructor
@RequestMapping("/product")
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "상품 교환", description = "dto와 인가를 통해서 상품교환을 진행한다.")
    @PostMapping("")
    public Long buyingProduct(@Authorization
                              @Parameter(hidden = true)
                              Long memberId,
                              @RequestBody BuyingRequest buyingRequest) {
        return paymentService.buyProduct(memberId, buyingRequest.price(), buyingRequest.paymentItemName());
    }

    @Operation(summary = "상품 교환 내역 조회", description = "인가를 통해서 상품교환 내역을 확인한다.")
    @GetMapping("/history")
    public List<PaymentHistory> getPaymentHistory(
            @Authorization
            @Parameter(hidden = true)
            Long memberId) {
        return paymentService.getPaymentHistoryList(memberId);
    }

    @Operation(summary = "상품 교환 내역 조회 갯수 카운트", description = "인가를 통해서 상품교환 내역 갯수 를 확인한다.")
    @GetMapping("/history/counting")
    public Long countPaymentHistory(
            @Authorization
            @Parameter(hidden = true)
            Long memberId) {
        return paymentService.countPaymentHistory(memberId);
    }
}
