package com.surbear.member.repository;

import com.surbear.member.entity.MemberEntity;
import com.surbear.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    @Query("SELECT COUNT(u) FROM MemberEntity u WHERE u.email = :email AND u.deleted = false")
    Long countByEmail(String email);

    @Query("SELECT COUNT(u) FROM MemberEntity u WHERE u.userId = :userId AND u.deleted = false")
    Long countByUserId(String userId);

    @Query("SELECT COUNT(u) FROM MemberEntity u WHERE u.nickname = :nickname AND u.deleted = false")
    Long countByNickname(String nickname);

    Member findByUserIdAndEmail(String userId, String email);

    Member findByUserId(String userId);
    Member findByNickname(String nickname);

    Member findByEmail(String email);
}
