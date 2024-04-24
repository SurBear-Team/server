package com.surbear.member.mapper;

import com.surbear.member.entity.RoleEntity;
import com.surbear.member.model.Role;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {

    RoleEntity toEntity(Role model);

    Role toModel(RoleEntity entity);
}
