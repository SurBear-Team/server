CREATE TABLE IF NOT EXISTS serveys_reports
(
    id          BIGINT AUTO_INCREMENT               NOT NULL COMMENT 'ID',
    servey_id   BIGINT                              NOT NULL COMMENT '신고당한 설문 ID',
    reporter_id BIGINT                              NOT NULL COMMENT '신고자 ID',
    reason      VARCHAR(255)                        NOT NULL COMMENT '신고사유',
    deleted     BIT       DEFAULT 0                 NOT NULL COMMENT '삭제 여부',
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간',
    PRIMARY KEY (id)
);