CREATE TABLE IF NOT EXISTS serveys_answers
(
    id            BIGINT AUTO_INCREMENT               NOT NULL COMMENT 'ID',
    servey_id     BIGINT                              NOT NULL COMMENT '설문 ID',
    respondent_id BIGINT                              NOT NULL COMMENT '설문 생성자 ID',
    deleted       BIT       DEFAULT 0                 NOT NULL COMMENT '삭제 여부',
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간',
    PRIMARY KEY (id)
);