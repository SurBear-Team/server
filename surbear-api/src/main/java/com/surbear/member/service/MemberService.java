package com.surbear.member.service;

import com.surbear.member.entity.MemberEntity;
import com.surbear.member.mapper.MemberMapper;
import com.surbear.member.model.Member;
import com.surbear.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {
    private final MemberRepository repository;
    private final MemberMapper mapper;







    private Long create(Member member) {
        MemberEntity memberEntity = mapper.toEnitity(member);
        return repository.save(memberEntity).getId();
    }

    private Member get(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return mapper.toModel(entity);
    }
}
