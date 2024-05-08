package com.surbear.payment.service;

import com.surbear.exception.ProcessErrorCodeException;
import com.surbear.exception.errorcode.BasicErrorCode;
import com.surbear.member.entity.MemberEntity;
import com.surbear.member.repository.MemberRepository;
import com.surbear.payment.entity.PaymentHistoryEntity;
import com.surbear.payment.mapper.PaymentMapper;
import com.surbear.payment.model.PaymentHistory;
import com.surbear.payment.repository.PaymentHistoryRepository;
import com.surbear.report.entity.SurveyReportEntity;
import com.surbear.report.model.SurveyReport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PaymentService {

    private final PaymentHistoryRepository paymentHistoryRepository;
    private final PaymentMapper paymentMapper;
    private final MemberRepository memberRepository;


    @Transactional
    public Long buyProduct(Long memberId, int price, String paymentItemName) {
        MemberEntity memberEntity = memberRepository.findById(memberId).get();
        int memberPoint = memberEntity.getPoint();


        if (memberPoint >= price) {
            memberEntity.setPoint(memberPoint -= price);
            memberRepository.save(memberEntity);

        } else {
            throw new ProcessErrorCodeException(BasicErrorCode.INSUFFICIENT_POINTS);
        }

        PaymentHistory paymentHistory = PaymentHistory.builder()
                .memberId(memberId)
                .paymentItem(paymentItemName)
                .deleted(false)
                .usedPoint(price)
                .build();

        return create(paymentHistory);
    }

    private Long create(PaymentHistory paymentHistory) {
        PaymentHistoryEntity newEntity = paymentMapper.toEntity(paymentHistory);

        PaymentHistoryEntity savedEntity = paymentHistoryRepository.save(newEntity);
        return savedEntity.getId();
    }

}
