CREATE TABLE IF NOT EXISTS surveys
(
    id                       BIGINT AUTO_INCREMENT                            NOT NULL COMMENT 'ID',
    survey_author_id         BIGINT                                           NOT NULL COMMENT '설문 생성자 ID',
    ongoing_type             ENUM ('PROGRESS','PAUSE','CLOSE','MODIFICATION') NOT NULL COMMENT '진행중 여부',
    survey_type              ENUM ('EDUCATION','ETC')                         NOT NULL COMMENT '설문 여부',
    point                    INT                                              NOT NULL COMMENT '제공하는 포인트',
    maximum_number_of_people INT                                              NOT NULL COMMENT '최대인원',
    description              VARCHAR(255)                                     NOT NULL COMMENT '설문에 대한 설명',
    title                    VARCHAR(255)                                     NOT NULL COMMENT '설문 제목',
    open_type                BIT       DEFAULT 0                              NOT NULL COMMENT '설문 제목',
    deleted                  BIT       DEFAULT 0                              NOT NULL COMMENT '삭제 여부',
    created_at               TIMESTAMP DEFAULT CURRENT_TIMESTAMP              NOT NULL COMMENT '생성 시간',
    updated_at               TIMESTAMP DEFAULT CURRENT_TIMESTAMP              NOT NULL COMMENT '수정 시간',
    dead_line                TIMESTAMP DEFAULT CURRENT_TIMESTAMP              NOT NULL COMMENT '설문 종료 시간',
    PRIMARY KEY (id)
);