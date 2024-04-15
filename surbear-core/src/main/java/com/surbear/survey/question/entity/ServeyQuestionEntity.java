package com.surbear.survey.question.entity;

import com.surbear.common.entity.BaseTimeEntity;
import com.surbear.survey.constants.QuestionType;
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
@Table(name = "questions")
public class ServeyQuestionEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Long id;

    @Column
    Long serveyId;

    @Column
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @Column
    String content;

    @Column
    Boolean required;

    @Column
    Integer page;

    @Column
    Integer maxText;

    @Builder.Default
    @Column
    boolean deleted = false;

}
