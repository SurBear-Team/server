package com.surbear.survey.entity;

계import com.surbear.common.entity.BaseTimeEntity;
import com.surbear.common.entity.BaseTimeEntity;
import com.surbear.survey.model.QuestionType;
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
@Table(name = "answers")
public class SurveyAnswerEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "answer_id")
    Long id;

    @Column
    String questionId;

    @Column
    String answer;

    @Column
    QuestionType questionType;

    @Builder.Default
    @Column
    boolean deleted = false;

}
