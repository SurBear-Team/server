package com.surbear.mock.example.mapper;

import com.surbear.mock.example.entity.example.ExampleEntity;
import com.surbear.mock.example.model.Example;
import org.mapstruct.Mapper;

@Mapper
public interface ExampleMapper {
    ExampleEntity toEntity(Example model);

    Example toModel(ExampleEntity entity);
}
