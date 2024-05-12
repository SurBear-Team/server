package com.surbear.payment.service;

import com.surbear.exception.ProcessErrorCodeException;
import com.surbear.exception.errorcode.BasicErrorCode;
import com.surbear.history.point.model.PointHistory;
import com.surbear.history.point.service.PointHistoryService;
import com.surbear.member.entity.MemberEntity;
import com.surbear.member.model.Member;
import com.surbear.member.repository.MemberRepository;
import com.surbear.payment.entity.PaymentHistoryEntity;
import com.surbear.payment.mapper.PaymentMapper;
import com.surbear.payment.model.PaymentHistory;
import com.surbear.payment.repository.PaymentHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PaymentService {

    private final PaymentHistoryRepository paymentHistoryRepository;
    private final PaymentMapper paymentMapper;
    private final MemberRepository memberRepository;
    private final PointHistoryService pointHistoryService;


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

        PointHistory pointHistory = pointHistoryService.createPointHistoryByBuyProduct(memberId, price);
        pointHistoryService.create(pointHistory);

        return create(paymentHistory);
    }

    public List<PaymentHistory> getPaymentHistoryList(Long memberId) {
        return paymentHistoryRepository.findAllByMemberId(memberId);
    }

    public List<PaymentHistory> getPaymentHistoryList(String nickname) {
        Member member = memberRepository.findByNicknameAndDeletedIsFalse(nickname);
        return paymentHistoryRepository.findAllByMemberId(member.id());
    }

    public Long countPaymentHistory(Long memberId) {
        return paymentHistoryRepository.countAllByMemberId(memberId);
    }

    private Long create(PaymentHistory paymentHistory) {
        PaymentHistoryEntity newEntity = paymentMapper.toEntity(paymentHistory);

        PaymentHistoryEntity savedEntity = paymentHistoryRepository.save(newEntity);
        return savedEntity.getId();
    }

}
