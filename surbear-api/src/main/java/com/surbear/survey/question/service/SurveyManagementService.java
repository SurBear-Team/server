package com.surbear.survey.question.service;

import com.surbear.survey.dto.UpdateSurveyOngoingTypeRequest;
import com.surbear.survey.dto.UpdateSurveyRequest;
import com.surbear.survey.question.entity.SurveyQuestionEntity;
import com.surbear.survey.question.model.Survey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SurveyManagementService {

    private final SurveyPrecedingService precedingService;


    public Survey getSurvey(Long surveyId) {
        return precedingService.getSurvey(surveyId);
    }

    public List<Survey> getSurveyList(Long surveyAuthorId) {
        return precedingService.getSurveyByAuthorId(surveyAuthorId);
    }

    public List<Survey> getSurveyByCreatedAt(int page, int number){
        return precedingService.getSurveyByCreatedAt(page, number);
    }

    @Transactional
    public int updateSurvey(UpdateSurveyRequest req, Long surveyId) {
        return precedingService.updateSurvey(req, surveyId);
    }

    @Transactional
    public void updateSurveyOnGoingType(UpdateSurveyOngoingTypeRequest req) {
        precedingService.updateSurveyOnGoingType(req);
    }

    @Transactional
    public void deleteSurvey(Long surveyId) {
        boolean hasQuestions = true;

        precedingService.deleteSurvey(surveyId);

        while (hasQuestions) {
            SurveyQuestionEntity entity = precedingService.getSurveyQuestionDeletedIsFalse(surveyId);
            if (entity != null) {
                entity.delete();

                precedingService.deleteSurveyQuestionOptionList(entity.getId());
            } else {
                hasQuestions = false;
            }
        }
    }
}
