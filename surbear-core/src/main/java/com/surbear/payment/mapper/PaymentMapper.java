package com.surbear.payment.mapper;

import com.surbear.payment.entity.PaymentHistoryEntity;
import com.surbear.payment.model.PaymentHistory;
import org.mapstruct.Mapper;

@Mapper
public interface PaymentMapper {
    PaymentHistoryEntity toEntity(PaymentHistory model);

    PaymentHistory toModel(PaymentHistoryEntity entity);
}
