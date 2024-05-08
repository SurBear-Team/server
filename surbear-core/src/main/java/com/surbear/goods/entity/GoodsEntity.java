package com.surbear.goods.entity;


import com.surbear.common.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "goods")
public class GoodsEntity extends BaseTimeEntity {

    @Id
    @EqualsAndHashCode.Include
    String goodsCode;
    @Column
    String salePrice;
    @Column
    String goodsImgS;
    @Column
    String goodsName;
    @Column
    String brandName;
    @Column
    String goodsTypeDtlNm;

}
