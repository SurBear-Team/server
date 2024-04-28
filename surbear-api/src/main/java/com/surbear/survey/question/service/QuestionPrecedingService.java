package com.surbear.survey.question.service;


import com.surbear.survey.constants.OngoingType;
import com.surbear.survey.constants.QuestionType;
import com.surbear.survey.constants.SurveyType;
import com.surbear.survey.dto.*;
import com.surbear.survey.question.entity.SurveyEntity;
import com.surbear.survey.question.entity.SurveyQuestionEntity;
import com.surbear.survey.question.entity.SurveyQuestionOptionEntity;
import com.surbear.survey.question.mapper.SurveyMapper;
import com.surbear.survey.question.mapper.SurveyQuestionMapper;
import com.surbear.survey.question.mapper.SurveyQuestionOptionMapper;
import com.surbear.survey.question.model.Survey;
import com.surbear.survey.question.model.SurveyQuestion;
import com.surbear.survey.question.model.SurveyQuestionOption;
import com.surbear.survey.question.repository.SurveyQuestionOptionRepository;
import com.surbear.survey.question.repository.SurveyQuestionRepository;
import com.surbear.survey.question.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class QuestionPrecedingService {

    private final SurveyQuestionOptionRepository surveyQuestionOptionRepository;
    private final SurveyQuestionRepository surveyQuestionRepository;
    private final SurveyRepository surveyRepository;
    private final SurveyQuestionOptionMapper surveyQuestionOptionMapper;
    private final SurveyQuestionMapper surveyQuestionMapper;
    private final SurveyMapper surveyMapper;


    //CREATE

    @Transactional
    public Long createSurvey(CreateSurveyRequest req) {
        SurveyEntity newEntity = surveyMapper.createSurveyRequestToEntity(req);

        SurveyEntity savedEntity = surveyRepository.save(newEntity);

        return savedEntity.getId();
    }

    @Transactional
    public Long createSurveyQuestion(SurveyQuestion surveyQuestion) {
        SurveyQuestionEntity newEntity = surveyQuestionMapper.toEntity(surveyQuestion);

        SurveyQuestionEntity savedEntity = surveyQuestionRepository.save(newEntity);

        return savedEntity.getId();
    }

    @Transactional
    public void createSurveyQuestionOption(String answer, Long questionId) {
        SurveyQuestionOption surveyQuestionOption = SurveyQuestionOption.builder()
                .questionId(questionId)
                .answer(answer)
                .build();
        SurveyQuestionOptionEntity newEntity = surveyQuestionOptionMapper.toEntity(surveyQuestionOption);

        surveyQuestionOptionRepository.save(newEntity);
    }

    //GET


    public Survey getSurvey(Long surveyId) {
        return surveyRepository.findById(surveyId)
                .map(surveyMapper::toModel)
                .orElse(null);
    }

    public List<Survey> getSurveyByAuthorId(Long surveyAuthorId) {
        return surveyRepository.findBySurveyAuthorId(surveyAuthorId);
    }

    public SurveyQuestionEntity getSurveyQuestion(Long surveyId) {
        return surveyQuestionRepository.findBySurveyId(surveyId);
    }

    public List<SurveyQuestion> getAllSurveyQuestionsId(Long surveyId){
        return surveyQuestionRepository.findAllBySurveyId(surveyId);
    }

    public List<SurveyQuestionOption> getSurveyQuestionOption(Long surveyQuestionId) {
        return surveyQuestionOptionRepository.findByQuestionId(surveyQuestionId);
    }

    public List<SurveyQuestionOptionsList> findAnswersByQuestionId(Long surveyQuestionId){
        List<SurveyQuestionOption> options = getSurveyQuestionOption(surveyQuestionId);

        return options.stream()
                .map(option -> new SurveyQuestionOptionsList(option.id(), option.answer()))
                .collect(Collectors.toList());
    }

    public SurveyQuestionEntity getSurveyQuestionDeletedIsFalse(Long surveyId) {
        return surveyQuestionRepository.findFirstBySurveyIdAndDeletedIsFalse(surveyId);
    }

    public Page<Survey> getSurveyByCreatedAt(int page, int number, SurveyType type) {
        Pageable pageable = PageRequest.of(page, number, Sort.by(Sort.Direction.DESC, "startDate"));

        return (type == SurveyType.ALL) ? surveyRepository.findByDeletedFalseAndOngoingTypeOrderByStartDateDesc(OngoingType.PROGRESS, pageable)
                : surveyRepository.findByDeletedFalseAndOngoingTypeAndSurveyTypeOrderByStartDateDesc(OngoingType.PROGRESS, type, pageable);

    }

    //UPDATE

    @Transactional
    public int updateSurvey(UpdateSurveyRequest req, Long surveyId) {
        return surveyRepository.updateSurvey(req, surveyId);
    }

    @Transactional
    public void updateSurveyOnGoingType(UpdateSurveyOngoingTypeRequest req) {
        SurveyEntity newEntity = surveyRepository.findById(req.id()).get();
        newEntity.setOngoingType(req.type());
    }

    //DELETE

    @Transactional
    public void deleteSurvey(Long surveyId) {

        SurveyEntity entity = surveyRepository.findById(surveyId)
                .orElseThrow(RuntimeException::new);

        entity.delete();
    }

    @Transactional
    public void deleteSurveyQuestionOptionList(Long surveyQuestionId) {
        surveyQuestionOptionRepository.markDeletedByQuestionId(surveyQuestionId);
    }
}
