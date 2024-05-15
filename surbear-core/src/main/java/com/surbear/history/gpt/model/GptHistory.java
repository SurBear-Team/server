package com.surbear.history.gpt.model;


public record GptHistory(
        Long id,
        Integer prompt_tokens,
        Integer completion_tokens,
        Integer total_tokens
) {
}
