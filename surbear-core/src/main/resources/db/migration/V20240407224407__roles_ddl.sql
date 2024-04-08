CREATE TABLE IF NOT EXISTS roles
(
    id         BIGINT AUTO_INCREMENT               NOT NULL COMMENT 'ID',
    member_id  BIGINT                              NOT NULL COMMENT '멤버 ID',
    role       ENUM ('USER','ADMIN')               NOT NULL COMMENT '역할',
    deleted    BIT       DEFAULT 0                 NOT NULL COMMENT '삭제 여부',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간',
    PRIMARY KEY (id)
);