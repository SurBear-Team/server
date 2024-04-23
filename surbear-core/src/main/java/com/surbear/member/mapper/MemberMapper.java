package com.surbear.member.mapper;

import com.surbear.member.entity.MemberEntity;
import com.surbear.member.model.Member;
import org.mapstruct.Mapper;

@Mapper
public interface MemberMapper {
    MemberEntity toEntity(Member model);

    Member toModel(MemberEntity entity);
}
