CREATE TABLE IF NOT EXISTS members
(
    id         BIGINT AUTO_INCREMENT NOT NULL COMMENT 'ID',
    nickname   VARCHAR(100)                         NOT NULL COMMENT '멤버 명',
    email      VARCHAR(200)                         NOT NULL COMMENT '닉네임',
    user_id    VARCHAR(200)                         NOT NULL COMMENT '아이디',
    password   VARCHAR(200)                         NOT NULL COMMENT '패스워드',
    point      INT                                  NOT NULL COMMENT '포인트',
    age        ENUM ('UNDER_TWENTY', 'TWENTIES', 'THIRTIES', 'FOURTIES', 'FIFTIES', 'OVER_SIXTIES') NOT NULL COMMENT '나이',
    gender     ENUM ('FEMALE', 'MALE', 'UNKNOWN') NOT NULL COMMENT '성별',
    deleted    BIT       DEFAULT 0                  NOT NULL COMMENT '삭제 여부',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간',
    PRIMARY KEY (id)
);