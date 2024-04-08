CREATE TABLE IF NOT EXISTS points_histories
(
    id           BIGINT AUTO_INCREMENT               NOT NULL COMMENT 'ID',
    payer_id     BIGINT                              NOT NULL COMMENT '포인트 지급자ID',
    recipient_id BIGINT                              NOT NULL COMMENT '포인트 수령자ID',
    description  VARCHAR(255)                        NOT NULL COMMENT '포인트 수령 정보',
    paid_point   INT                                 NOT NULL COMMENT '지급 포인트',
    deleted      BIT       DEFAULT 0                 NOT NULL COMMENT '삭제 여부',
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간',
    PRIMARY KEY (id)
);