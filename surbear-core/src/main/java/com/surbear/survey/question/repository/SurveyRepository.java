package com.surbear.survey.question.repository;

import com.surbear.survey.constants.OngoingType;
import com.surbear.survey.constants.SurveyType;
import com.surbear.survey.dto.UpdateSurveyRequest;
import com.surbear.survey.question.entity.SurveyEntity;
import com.surbear.survey.question.model.Survey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SurveyRepository extends JpaRepository<SurveyEntity, Long> {

    @Transactional
    @Modifying
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

    List<SurveyEntity> findAllBySurveyAuthorId(Long memberId);

    List<Survey> findAllBySurveyAuthorIdAndDeletedIsFalse(Long memberId);

    List<Survey> findAllBySurveyAuthorIdAndDeletedIsFalseAndOngoingType(Long memberId, OngoingType ongoingType);

    @Query("SELECT s FROM SurveyEntity s WHERE s.id IN :ids")
    List<SurveyEntity> findAllByIds(List<Long> ids);

    Page<Survey> findByDeletedFalseAndOngoingTypeOrderByStartDateDesc(OngoingType ongoingType, Pageable pageable);

    Page<Survey> findByDeletedFalseAndOngoingTypeAndSurveyTypeOrderByStartDateDesc(OngoingType ongoingType, SurveyType surveyType, Pageable pageable);

    Integer countAllBySurveyAuthorId(Long memberId);
    List<SurveyEntity> findAllByOngoingTypeInAndDeletedFalse(List<OngoingType> ongoingTypes);



}
