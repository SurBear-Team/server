package com.surbear.survey.question.repository;

import com.surbear.survey.dto.GetSurveyListResponse;
import com.surbear.survey.dto.UpdateSurveyRequest;
import com.surbear.survey.question.entity.SurveyEntity;
import com.surbear.survey.question.entity.SurveyQuestionEntity;
import com.surbear.survey.question.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SurveyRepository extends JpaRepository<SurveyEntity, Long> {

    @Query("""
            UPDATE SurveyEntity s
            SET s.title = :#{#dto.title},
                s.description = :#{#dto.description},
                s.surveyType = :#{#dto.surveyType},
                s.openType = :#{#dto.openType},
                s.maximumNumberOfPeople = :#{#dto.maximumNumberOfPeople},
                s.deadLine = :#{#dto.deadLine}
            WHERE s.id = :id
            """)
    int updateSurvey(UpdateSurveyRequest dto, Long id);

    List<Survey> findBySurveyAuthorId(Long surveyAuthorId);

}
