package com.surbear.mock.example.repository;

import com.surbear.mock.example.entity.example.ExampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends JpaRepository<ExampleEntity,Long> {
}
