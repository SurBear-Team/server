package com.surbear.mock.example.model;

import lombok.Builder;

@Builder
public record Example(
        Long id,
        String name
) {
}
