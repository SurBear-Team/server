CREATE TABLE IF NOT EXISTS survey_questions
(
    id            BIGINT AUTO_INCREMENT                         NOT NULL COMMENT 'ID',
    survey_id     BIGINT                                        NOT NULL COMMENT '설문 ID',
    question_type ENUM ('MULTIPLE_CHOICE','SUBJECTIVE','RATIO') NOT NULL COMMENT '질문 타입',
    content       VARCHAR(255)                                  NOT NULL COMMENT '질문 내용',
    max_text      INT                                           NOT NULL COMMENT '최대 텍스트 제한',
    page          INT                                           NOT NULL COMMENT '현재 페이지 표시',
    required      BIT       DEFAULT 0                           NOT NULL COMMENT '필수 여부',
    deleted       BIT       DEFAULT 0                           NOT NULL COMMENT '삭제 여부',
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP           NOT NULL COMMENT '생성 시간',
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP           NOT NULL COMMENT '수정 시간',
    PRIMARY KEY (id)
);