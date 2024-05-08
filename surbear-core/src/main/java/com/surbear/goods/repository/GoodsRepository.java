package com.surbear.goods.repository;

import com.surbear.goods.entity.GoodsEntity;
import com.surbear.goods.model.Goods;
import com.surbear.survey.constants.OngoingType;
import com.surbear.survey.question.model.Survey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends JpaRepository<GoodsEntity, String> {
    Page<GoodsEntity> findAll(Pageable pageable);

}
