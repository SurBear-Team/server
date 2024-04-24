package com.surbear.member.repository;

import com.surbear.member.entity.RoleEntity;
import com.surbear.member.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    @Query("SELECT COUNT(u) FROM RoleEntity u WHERE u.memberId = :memberId AND u.deleted = false")
    Long countByMemberId(Long memberId);


}
