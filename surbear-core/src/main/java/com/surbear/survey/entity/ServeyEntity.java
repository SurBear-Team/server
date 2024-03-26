package com.surbear.survey.entity;

import com.surbear.member.entity.MemberEntity;
import com.surbear.member.model.Role;
import com.surbear.survey.model.SituationType;
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
@Table(name = "servey")
public class ServeyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Long id;

    @Column
    String title;

    @Column
    String description;

    @Column
    Integer paymentPoint;

    @Column
    Boolean deleted;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private SituationType situationType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private SurveyType surveyType;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity surveyAuthor;



}
