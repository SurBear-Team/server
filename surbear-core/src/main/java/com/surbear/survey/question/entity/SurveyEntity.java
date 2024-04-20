package com.surbear.survey.question.entity;

import com.surbear.common.entity.BaseTimeEntity;
import com.surbear.survey.constants.OngoingType;
import com.surbear.survey.constants.SurveyType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "surveys")
public class SurveyEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Long id;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private OngoingType ongoingType = OngoingType.PAUSE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private SurveyType surveyType;

    @Column
    Long surveyAuthorId;

    @Column
    String title;

    @Column
    String description;

    @Builder.Default
    @Column
    Integer point = 999;

    @Column
    Integer maximumNumberOfPeople;

    @Column
    boolean openType;

    @Builder.Default
    @Column
    boolean deleted = false;

    @Column
    Instant deadLine;

    @Column(nullable = true)
    private Instant startDate;

    public void delete() {
        deleted = true;
    }

    public void setOngoingType(OngoingType type) {
        this.ongoingType = type;
        if (type == OngoingType.PROGRESS) {
            this.startDate = Instant.now();
        }

    }
}
