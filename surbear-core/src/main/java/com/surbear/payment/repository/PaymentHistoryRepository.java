package com.surbear.payment.repository;

import com.surbear.payment.entity.PaymentHistoryEntity;
import com.surbear.payment.model.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistoryEntity, Long> {

    List<PaymentHistory> findAllByMemberId(Long memberId);
}
