package com.surbear.survey.question.service;

import com.surbear.survey.dto.*;
import com.surbear.survey.dto.update.survey.AfterChangeSurveyQuestionOptionList;
import com.surbear.survey.dto.update.survey.ComparisonQuestionAndAnswer;
import com.surbear.survey.question.entity.SurveyQuestionEntity;
import com.surbear.survey.question.entity.SurveyQuestionOptionEntity;
import com.surbear.survey.question.model.SurveyQuestion;
import com.surbear.survey.question.model.SurveyQuestionOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SurveyQuestionService {

    private final QuestionPrecedingService precedingService;


    @Transactional
    public Long createSurvey(CreateSurveyRequest req, Long authorId) {
        //TODO 지급 포인트 로직
        //TODO 순서 로직
        CreateSurveyRequest createSurveyRequest = CreateSurveyRequest.builder()
                .surveyAuthorId(authorId)
                .surveyType(req.surveyType())
                .maximumNumberOfPeople(req.maximumNumberOfPeople())
                .title(req.title())
                .description(req.description())
                .openType(req.openType())
                .deadLine(req.deadLine())
                .build();
        return precedingService.createSurvey(createSurveyRequest);
    }

    @Transactional
    public Long createQuestion(SurveyQuestion surveyQuestion, List<String> answers) {
        Long questionId = generateQuestion(surveyQuestion);
        generateQuestionOption(answers, questionId);
        return questionId;
    }

    @Transactional
    public boolean updateSurveyQuestionAndOptions(SurveyQuestion surveyQuestion, List<ComparisonQuestionAndAnswer> options) {
        Long questionId = surveyQuestion.id();
        SurveyQuestion newModel = precedingService.getSurveyQuestion(questionId);
        if (newModel.questionType() != surveyQuestion.questionType()) {
            deleteOptionsList(questionId);
        }
        updateQuestion(surveyQuestion);
        branchOptions(options, questionId);
        return true;
    }

    private Long generateQuestion(SurveyQuestion surveyQuestion) {
        return precedingService.createSurveyQuestion(surveyQuestion);
    }

    private void generateQuestionOption(List<String> list, Long questionId) {
        for (String answer : list) {
            precedingService.createSurveyQuestionOption(answer, questionId);
        }
    }

    private void updateQuestion(SurveyQuestion surveyQuestion) {
        SurveyQuestionEntity newEntity = SurveyQuestionEntity.builder()
                .id(surveyQuestion.id())
                .surveyId(surveyQuestion.surveyId())
                .questionType(surveyQuestion.questionType())
                .content(surveyQuestion.content())
                .required(surveyQuestion.required())
                .page(surveyQuestion.page())
                .questionOrder(surveyQuestion.questionOrder())
                .maxText(surveyQuestion.maxText())
                .deleted(surveyQuestion.deleted())
                .build();
        precedingService.updateSurveyQuestion(newEntity);
    }

    private void branchOptions(List<ComparisonQuestionAndAnswer> options, Long surveyOptionId) {
        for (ComparisonQuestionAndAnswer list : options) {
            if (list.afterChangeSurveyQuestionOptionList().deleteFlag()) {
                deleteOptions(list);
            } else if (list.afterChangeSurveyQuestionOptionList().creationFlag()) {
                createOptions(list, surveyOptionId);
            } else {
                updateOptions(list, surveyOptionId);
            }
        }

    }

    private void deleteOptions(ComparisonQuestionAndAnswer comparisonQuestionAndAnswer) {
        SurveyQuestionOptionEntity surveyQuestionOptionEntity = precedingService.getSurveyQuestionOption(comparisonQuestionAndAnswer.beforeChangeSurveyQuestionOptionList().id());
        surveyQuestionOptionEntity.delete();
        precedingService.deleteSurveyQuestionOption(surveyQuestionOptionEntity);
    }

    private void deleteOptionsList(Long questionId) {
        precedingService.deleteSurveyQuestionOptionList(questionId);
    }

    private void createOptions(ComparisonQuestionAndAnswer comparisonQuestionAndAnswer, Long surveyOptionId) {
        AfterChangeSurveyQuestionOptionList list = comparisonQuestionAndAnswer.afterChangeSurveyQuestionOptionList();
        precedingService.createSurveyQuestionOption(list.answer(), surveyOptionId);
    }

    private void updateOptions(ComparisonQuestionAndAnswer comparisonQuestionAndAnswer, Long surveyOptionId) {
        AfterChangeSurveyQuestionOptionList list = comparisonQuestionAndAnswer.afterChangeSurveyQuestionOptionList();
        SurveyQuestionOptionEntity newEntity = SurveyQuestionOptionEntity.builder()
                .id(list.id())
                .questionId(surveyOptionId)
                .answer(list.answer())
                .deleted(false)
                .build();
        precedingService.updateSurveyQuestionOption(newEntity);
    }
}
