package com.surbear.goods.repository;

import com.surbear.goods.entity.GoodsEntity;
import com.surbear.goods.model.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<GoodsEntity, String> {
    Page<GoodsEntity> findAll(Pageable pageable);

    @Query("SELECT g FROM GoodsEntity g WHERE g.goodsName LIKE %?1%")
    List<GoodsEntity> findByGoodsNameContaining(String goodsName);
}
