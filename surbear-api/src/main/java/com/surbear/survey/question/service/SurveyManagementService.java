package com.surbear.survey.question.service;

import com.surbear.survey.dto.UpdateSurveyRequest;
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

    public int updateSurvey(UpdateSurveyRequest req, Long surveyId) {
        return precedingService.updateSurvey(req, surveyId);
    }

    @Transactional
    public void deleteSurvey(Long surveyId) {
        precedingService.deleteSurvey(surveyId);
    }
}
