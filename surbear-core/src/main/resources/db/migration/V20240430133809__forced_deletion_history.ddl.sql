CREATE TABLE IF NOT EXISTS forced_deletion_history
(
    id                BIGINT AUTO_INCREMENT               NOT NULL COMMENT 'ID',
    admin_member_id   BIGINT                              NOT NULL COMMENT '강제삭제 어드민 ID',
    deleted_survey_id BIGINT                              NOT NULL COMMENT '삭제된 설문 ID',
    deleted           BIT       DEFAULT 0                 NOT NULL COMMENT '삭제 여부',
    created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
    updated_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간',
    PRIMARY KEY (id)
);