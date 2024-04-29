package com.surbear.report.mapper;

import com.surbear.report.entity.SurveyReportEntity;
import com.surbear.report.model.SurveyReport;
import org.mapstruct.Mapper;

@Mapper
public interface SurveyReportMapper {
    SurveyReportEntity toEntity(SurveyReport model);

    SurveyReport toModel(SurveyReportEntity entity);
}
