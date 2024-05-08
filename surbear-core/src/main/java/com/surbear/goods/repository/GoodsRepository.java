package com.surbear.goods.repository;

import com.surbear.goods.entity.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends JpaRepository<GoodsEntity, String> {
}
