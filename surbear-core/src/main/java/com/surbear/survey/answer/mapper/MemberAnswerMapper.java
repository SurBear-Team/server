package com.surbear.survey.answer.mapper;

import com.surbear.mock.example.entity.example.ExampleEntity;
import com.surbear.mock.example.model.Example;
import com.surbear.survey.answer.entity.MemberAnswerEntity;
import com.surbear.survey.answer.model.MemberAnswer;
import org.mapstruct.Mapper;

@Mapper
public interface MemberAnswerMapper {
    MemberAnswerEntity toEntity(MemberAnswer model);

    MemberAnswer toModel(MemberAnswerEntity entity);
}
