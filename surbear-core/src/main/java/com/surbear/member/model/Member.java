package com.surbear.member.model;

public record Member(
        Long id,
        Age age,
        Gender gender,
        String userId,
        String password,
        String email,
        Integer point,
        String nickname,
        boolean deleted
) {
}
