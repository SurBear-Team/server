CREATE TABLE IF NOT EXISTS gpt_token_history
(
    id                BIGINT AUTO_INCREMENT               NOT NULL COMMENT 'ID',
    prompt_tokens     INT                                 NOT NULL COMMENT '프롬프트 토큰',
    completion_tokens INT                                 NOT NULL COMMENT '완료 토큰',
    total_tokens      INT                                 NOT NULL COMMENT '총합 토큰',
    created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
    updated_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간',
    PRIMARY KEY (id)
);