package com.surbear.survey.question.entity;

import com.surbear.common.entity.BaseTimeEntity;
import com.surbear.survey.constants.OngoingType;
import com.surbear.survey.constants.SurveyType;
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
@Table(name = "surveys")
public class SurveyEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private OngoingType ongoingType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private SurveyType surveyType;

    @Column
    Long surveyAuthorId;

    @Column
    String title;

    @Column
    String description;

    @Column
    Integer point;

    @Column
    Integer maximumNumberOfPeople;

    @Column
    boolean openType;

    @Builder.Default
    @Column
    boolean deleted = false;

}
