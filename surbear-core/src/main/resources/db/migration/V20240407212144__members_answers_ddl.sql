CREATE TABLE IF NOT EXISTS member_answers
(
    id                 BIGINT AUTO_INCREMENT               NOT NULL COMMENT 'ID',
    survey_answer_id   BIGINT                              NOT NULL COMMENT '답변지 ID',
    survey_question_id BIGINT                              NOT NULL COMMENT '질문 ID',
    answer             VARCHAR(255)                        NOT NULL COMMENT '답변',
    deleted            BIT       DEFAULT 0                 NOT NULL COMMENT '삭제 여부',
    created_at         TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
    updated_at         TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간',
    PRIMARY KEY (id)
);