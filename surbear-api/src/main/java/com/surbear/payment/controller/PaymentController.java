package com.surbear.payment.controller;


import com.surbear.common.authorization.Authorization;
import com.surbear.payment.dto.BuyingRequest;
import com.surbear.payment.service.PaymentService;
import com.surbear.report.model.SurveyReport;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "결제", description = "결제 관련 API")
@RequiredArgsConstructor
@RequestMapping("/product")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("")
    public Long buyingProduct(@Authorization
                              @Parameter(hidden = true)
                              Long memberId,
                              @RequestBody BuyingRequest buyingRequest) {
        return paymentService.buyProduct(memberId, buyingRequest.price(), buyingRequest.paymentItemName());
    }
}
