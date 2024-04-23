package com.surbear.member.repository;

import com.surbear.member.entity.MemberEntity;
import com.surbear.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Member findByUserId(String userId);
}
