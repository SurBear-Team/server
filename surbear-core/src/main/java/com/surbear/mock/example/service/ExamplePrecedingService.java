package com.surbear.mock.example.service;

import com.surbear.mock.example.entity.example.ExampleEntity;
import com.surbear.mock.example.mapper.ExampleMapper;
import com.surbear.mock.example.model.Example;
import com.surbear.mock.example.repository.ExampleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ExamplePrecedingService {
    private final ExampleRepository repository;
    private final ExampleMapper mapper;

    @Transactional
    public Long create(Example example) {
        ExampleEntity exampleEntity = mapper.toEntity(example);
        return repository.save(exampleEntity).getId();
    }

    public Example get(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return mapper.toModel(entity);
    }
}

