CREATE TABLE IF NOT EXISTS payments_histories
(
    id           BIGINT AUTO_INCREMENT               NOT NULL COMMENT 'ID',
    member_id    BIGINT                              NOT NULL COMMENT '멤버 ID',
    payment_item VARCHAR(255)                        NOT NULL COMMENT '결제품목',
    used_point   INT                                 NOT NULL COMMENT '사용 포인트',
    deleted      BIT       DEFAULT 0                 NOT NULL COMMENT '삭제 여부',
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간',
    PRIMARY KEY (id)
);