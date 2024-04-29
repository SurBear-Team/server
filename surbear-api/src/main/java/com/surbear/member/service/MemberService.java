package com.surbear.member.service;

import com.surbear.JwtTokenProvider;
import com.surbear.exception.ProcessErrorCodeException;
import com.surbear.exception.errorcode.BasicErrorCode;
import com.surbear.member.controller.dto.LoginRequest;
import com.surbear.member.controller.dto.LoginResponse;
import com.surbear.member.entity.MemberEntity;
import com.surbear.member.mapper.MemberMapper;
import com.surbear.member.model.Member;
import com.surbear.member.repository.MemberRepository;
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

    @Transactional
    public Long signUp(Member member) {
        return create(member);
    }

    public LoginResponse login(LoginRequest req) {
        Member newEntity = checkUserIdExists(req.userId());
        isAccountDeleted(newEntity);
        verifyPassword(req.password(), newEntity.password());
        return LoginResponse.builder()
                .accessToken(jwtTokenProvider.createToken(newEntity.id().toString()))
                .build();
    }

    public String findUserIdByEmail(String email) {
        Member newMember = repository.findByEmail(email);
        return newMember.userId();
    }

    @Transactional
    public boolean changePassword(String email, String newPassword) {
        Member member = repository.findByEmail(email);
        MemberEntity newEntity = mapper.toEntity(member);

        newEntity.setPassword(newPassword);
        repository.save(newEntity);
        return true;
    }

    public Member getMemberInfoByToken(Long memberId) {
        MemberEntity memberEntity = repository.findById(memberId).get();
        Member member = mapper.toModel(memberEntity);
        return member;

    }

    public boolean checkUserIdAndEmailExists(String userId, String email) {
        Member member = repository.findByUserIdAndEmail(userId, email);
        if (member == null) {
            throw new ProcessErrorCodeException(BasicErrorCode.USER_NOT_FOUND);
        }
        return true;
    }

    public boolean checkEmailExists(String email) {
        Member member = repository.findByEmail(email);
        if (member == null) {
            throw new ProcessErrorCodeException(BasicErrorCode.USER_NOT_FOUND);
        }
        return true;
    }

    public boolean checkDuplicate(String type, String value) {
        return switch (type) {
            case "email" -> isEmailDuplicate(value);
            case "userid" -> isUserIdDuplicate(value);
            case "nickname" -> isNickNameDuplicate(value);
            default -> throw new ProcessErrorCodeException(BasicErrorCode.INVALID_PARAMETER);
        };
    }

    private Member checkUserIdExists(String userId) {
        Member member = repository.findByUserId(userId);
        if (member == null) {
            throw new ProcessErrorCodeException(BasicErrorCode.USER_NOT_FOUND);
        }

        return member;
    }

    private boolean isAccountDeleted(Member member){
        if (member.deleted()){
            throw new ProcessErrorCodeException(BasicErrorCode.USER_NOT_FOUND);
        }
        return true;
    }

    private void verifyPassword(String passwordToVerify, String referencePassword) {
        if (!passwordToVerify.equals(referencePassword)) {
            throw new ProcessErrorCodeException(BasicErrorCode.PASSWORD_MISMATCH);
        }
    }

    private boolean isEmailDuplicate(String email) {
        boolean duplicateFlag = repository.countByEmail(email) > 0;
        if (duplicateFlag) {
            throw new ProcessErrorCodeException(BasicErrorCode.DUPLICATED_EMAIL);
        }
        return true;
    }

    private boolean isUserIdDuplicate(String userId) {
        boolean duplicateFlag = repository.countByUserId(userId) > 0;
        if (duplicateFlag) {
            throw new ProcessErrorCodeException(BasicErrorCode.DUPLICATED_USERID);
        }
        return true;
    }


    private boolean isNickNameDuplicate(String nickName) {
        boolean duplicateFlag = repository.countByNickname(nickName) > 0;

        if (duplicateFlag) {
            throw new ProcessErrorCodeException(BasicErrorCode.DUPLICATED_NICKNAME);
        }
        return true;
    }

    @Transactional
    public boolean delete(Long memberId) {
        MemberEntity memberEntity = repository.findById(memberId).get();

        memberEntity.delete();

        repository.save(memberEntity);

        return true;
    }


    private Long create(Member member) {
        MemberEntity memberEntity = mapper.toEntity(member);

        MemberEntity savedEntity = repository.save(memberEntity);
        return savedEntity.getId();
    }
}
