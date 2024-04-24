package com.surbear.survey.question.service;

import com.surbear.survey.constants.QuestionType;
import com.surbear.survey.constants.SurveyType;
import com.surbear.survey.dto.QuestionAndOptions;
import com.surbear.survey.dto.UpdateSurveyOngoingTypeRequest;
import com.surbear.survey.dto.UpdateSurveyRequest;
import com.surbear.survey.question.entity.SurveyQuestionEntity;
import com.surbear.survey.question.model.Survey;
import com.surbear.survey.question.model.SurveyQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SurveyManagementService {

    private final QuestionPrecedingService precedingService;


    public Survey getSurvey(Long surveyId) {
        return precedingService.getSurvey(surveyId);
    }

    @Transactional
    public List<QuestionAndOptions> createAllQuestionsAndOptions(Long surveyId) {
        List<SurveyQuestion> surveyQuestionEntities = precedingService.getAllSurveyQuestionsId(surveyId);

        return surveyQuestionEntities.stream()
                .map(this::createQuestionAndOptions)
                .collect(Collectors.toList());
    }

    private QuestionAndOptions createQuestionAndOptions(SurveyQuestion surveyQuestion) {
        return (surveyQuestion.questionType() == QuestionType.MULTIPLE_CHOICE)
                ?
                QuestionAndOptions
                        .ofObjectiveQuestion(surveyQuestion, precedingService.findAnswersByQuestionId(surveyQuestion.id()))
                :
                QuestionAndOptions
                        .ofSubjectiveQuestion(surveyQuestion);
    }

    public List<Survey> getSurveyList(Long surveyAuthorId) {
        return precedingService.getSurveyByAuthorId(surveyAuthorId);
    }

    public Page<Survey> getSurveyByCreatedAt(int page, int number, SurveyType type) {
        return precedingService.getSurveyByCreatedAt(page, number, type);
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
