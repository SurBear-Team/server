package com.surbear.history.point.entity;

import com.surbear.common.entity.BaseTimeEntity;
import com.surbear.history.point.constant.PaymentType;
import com.surbear.member.constant.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "points_histories")
public class PointHistoryEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Long id;

    @Column
    Long payerId;

    @Column
    Long recipientId;

    @Column
    String description;

    @Column
    Integer paidPoint;

    @Column
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Builder.Default
    @Column
    boolean deleted = false;

}
