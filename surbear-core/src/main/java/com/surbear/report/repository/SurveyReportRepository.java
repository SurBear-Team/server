package com.surbear.report.repository;

import com.surbear.report.entity.SurveyReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyReportRepository extends JpaRepository<SurveyReportEntity, Long> {
}
