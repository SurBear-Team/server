CREATE TABLE IF NOT EXISTS goods
(
    goods_code        VARCHAR(255)                        NOT NULL COMMENT '상품 ID',
    sale_price        VARCHAR(255)                        NOT NULL COMMENT '상품 가격',
    goods_imgs        VARCHAR(255)                        NOT NULL COMMENT '상품 이미지',
    goods_name        VARCHAR(255)                        NOT NULL COMMENT '상품 이름',
    brand_name        VARCHAR(255)                        NOT NULL COMMENT '브랜드 이름',
    goods_type_dtl_nm VARCHAR(255)                        NOT NULL COMMENT '상세 상품 유형',
    created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
    updated_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간',
    PRIMARY KEY (goods_code)
);