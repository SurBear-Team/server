CREATE TABLE IF NOT EXISTS members_answers
(
    id               BIGINT AUTO_INCREMENT               NOT NULL COMMENT 'ID',
    answer           VARCHAR(255)                        NOT NULL COMMENT '답변',
    survey_answer_id BIGINT                              NOT NULL COMMENT '답변지 ID',
    deleted          BIT       DEFAULT 0                 NOT NULL COMMENT '삭제 여부',
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
    updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간',
    PRIMARY KEY (id)
);