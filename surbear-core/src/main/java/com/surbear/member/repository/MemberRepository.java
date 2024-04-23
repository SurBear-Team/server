package com.surbear.member.repository;

import com.surbear.member.entity.MemberEntity;
import com.surbear.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    @Query("SELECT COUNT(u) FROM MemberEntity u WHERE u.email = :email AND u.deleted = false")
    Long countByEmail(String email);

    Member findByUserIdAndEmail(String userId, String email);

    Member findByUserId(String userId);

    Member findByEmail(String email);
}
