package com.surbear.member.model;

import com.surbear.member.constant.Permission;
import lombok.Builder;

@Builder
public record Role(
        Long id,
        Long memberId,
        Permission permission,
        boolean deleted
) {
}
