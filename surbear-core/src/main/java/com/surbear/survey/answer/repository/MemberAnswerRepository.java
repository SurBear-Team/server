package com.surbear.survey.answer.repository;

import com.surbear.survey.answer.entity.MemberAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAnswerRepository extends JpaRepository<MemberAnswerEntity, Long> {
}
