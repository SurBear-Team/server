package com.surbear.survey.entity;

import com.surbear.common.entity.BaseTimeEntity;
import com.surbear.survey.model.OngoingType;
import com.surbear.survey.model.SurveyType;
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
@Table(name = "serveys")
public class ServeyEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "servey_id")
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private OngoingType ongoingType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private SurveyType surveyType;

    @Column
    String serveyAuthorId;

    @Column
    String title;

    @Column
    String description;

    @Column
    Integer point;

    @Builder.Default
    @Column
    boolean deleted = false;

}
