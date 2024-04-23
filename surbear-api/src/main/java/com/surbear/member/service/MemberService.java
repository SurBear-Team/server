package com.surbear.member.service;

import com.surbear.JwtTokenProvider;
import com.surbear.exception.ProcessErrorCodeException;
import com.surbear.exception.errorcode.BasicErrorCode;
import com.surbear.member.controller.dto.CertificationRequest;
import com.surbear.member.controller.dto.LoginRequest;
import com.surbear.member.controller.dto.LoginResponse;
import com.surbear.member.entity.MemberEntity;
import com.surbear.member.mapper.MemberMapper;
import com.surbear.member.model.Member;
import com.surbear.member.repository.MemberRepository;
import com.surbear.survey.question.model.Survey;
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

    private final JwtTokenProvider jwtTokenProvider;


    public LoginResponse login(LoginRequest req) {
        Member newEntity = checkUserId(req.userId());

        checkPassword(req.password(), newEntity.password());
        return LoginResponse.builder()
                .accessToken(jwtTokenProvider.createToken(newEntity.id().toString()))
                .build();
    }

    private Member checkUserId(String userId){
        if(repository.findByUserId(userId)==null){
            throw new ProcessErrorCodeException(BasicErrorCode.USER_NOT_FOUND);
        }
        return repository.findByUserId(userId);
    }

    private void checkPassword(String passwordToVerify, String referencePassword) {
        if (!passwordToVerify.equals(referencePassword)) {
            throw new ProcessErrorCodeException(BasicErrorCode.PASSWORD_MISMATCH);
        }
    }


    @Transactional
    public Long create(Member member) {
        MemberEntity memberEntity = mapper.toEntity(member);

        MemberEntity savedEntity = repository.save(memberEntity);
        return savedEntity.getId();
    }
}
