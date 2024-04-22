package com.surbear.survey.answer.service;

import com.surbear.survey.answer.entity.MemberAnswerEntity;
import com.surbear.survey.answer.entity.SurveyAnswerEntity;
import com.surbear.survey.answer.mapper.MemberAnswerMapper;
import com.surbear.survey.answer.mapper.SurveyAnswerMapper;
import com.surbear.survey.answer.model.MemberAnswer;
import com.surbear.survey.answer.model.SurveyAnswer;
import com.surbear.survey.answer.repository.MemberAnswerRepository;
import com.surbear.survey.answer.repository.SurveyAnswerRepository;
import com.surbear.survey.dto.AnswerDto;
import com.surbear.survey.dto.QuestionAndOptions;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AnswerPrecedingService {
    private final SurveyAnswerRepository surveyAnswerRepository;
    private final SurveyAnswerMapper surveyAnswerMapper;
    private final MemberAnswerRepository memberAnswerRepository;
    private final MemberAnswerMapper memberAnswerMapper;

    @Transactional
    public Long createSurveyAnswer(SurveyAnswer surveyAnswer) {
        SurveyAnswerEntity newEntity = surveyAnswerMapper.toEntity(surveyAnswer);

        SurveyAnswerEntity savedEntity = surveyAnswerRepository.save(newEntity);

        return savedEntity.getId();
    }

    @Transactional
    public Long createMemberAnswer(MemberAnswer memberAnswer) {
        MemberAnswerEntity newEntity = memberAnswerMapper.toEntity(memberAnswer);

        MemberAnswerEntity savedEntity = memberAnswerRepository.save(newEntity);

        return savedEntity.getId();
    }

    @Transactional
    public void saveMemberAnswer(Long surveyAnswerId, Long questionId, List<String> answers) {
        answers.forEach(answer -> {
            MemberAnswer newDto = MemberAnswer.builder()
                    .surveyQuestionId(questionId)
                    .surveyAnswerId(surveyAnswerId)
                    .answer(answer)
                    .build();

            createMemberAnswer(newDto);
        });
    }

    public Long getSurveyAnswer(SurveyAnswer surveyAnswer) {
        return surveyAnswerRepository.findFirstByMemberIdAndSurveyIdAndDeletedIsFalse(surveyAnswer.memberId(), surveyAnswer.surveyId());
    }
}

