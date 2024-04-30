package com.surbear.role.service;


import com.surbear.exception.ProcessErrorCodeException;
import com.surbear.exception.errorcode.BasicErrorCode;
import com.surbear.member.constant.Permission;
import com.surbear.member.entity.MemberEntity;
import com.surbear.member.entity.RoleEntity;
import com.surbear.member.mapper.MemberMapper;
import com.surbear.member.mapper.RoleMapper;
import com.surbear.member.model.Member;
import com.surbear.member.model.Role;
import com.surbear.member.repository.MemberRepository;
import com.surbear.member.repository.RoleRepository;
import com.surbear.member.service.MemberService;
import com.surbear.role.controller.dto.AdminListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final MemberRepository memberRepository;
    private final RoleMapper roleMapper;


    @Transactional
    public Long adminRegistration(String nickname) {
        Member newEntity = memberRepository.findByNicknameAndDeletedIsFalseAndDeletedIsFalse(nickname);

        validAdmin(newEntity);
        checkDuplicationAdmin(newEntity.id());
        Role model = Role.builder()
                .memberId(newEntity.id())
                .permission(Permission.ADMIN)
                .build();

        return create(model);
    }

    @Transactional
    public boolean deleteRegistration(Long roleId) {
        RoleEntity roleEntity = roleRepository.findById(roleId).get();
        roleEntity.delete();

        roleRepository.save(roleEntity);
        return true;
    }

    public List<AdminListResponse> getAdminList() {
        List<RoleEntity> listRoleEntity = roleRepository.findAllByDeletedIsFalse();

        return listRoleEntity.stream().map(roleEntity -> {
            MemberEntity memberEntity = memberRepository.findById(roleEntity.getMemberId())
                    .orElseThrow(() -> new RuntimeException("Member not found for id: " + roleEntity.getMemberId()));

            return AdminListResponse.builder()
                    .memberId(roleEntity.getMemberId())
                    .roleId(roleEntity.getId())
                    .nickname(memberEntity.getNickname())
                    .build();
        }).collect(Collectors.toList());
    }

    public void checkPermissionExists(Long memberId) {
        Role newEntity = roleRepository.findByMemberIdAndDeletedIsFalse(memberId);
        if (newEntity == null) {
            throw new ProcessErrorCodeException(BasicErrorCode.ROLE_NOT_EXISTS);
        }
    }


    private void checkDuplicationAdmin(Long memberId) {
        boolean duplicateFlag = roleRepository.countByMemberId(memberId) > 0;
        if (duplicateFlag) {
            throw new ProcessErrorCodeException(BasicErrorCode.DUPLICATED_ID);
        }
    }


    private void validAdmin(Member member) {
        if (member == null) {
            throw new ProcessErrorCodeException(BasicErrorCode.USER_NOT_FOUND);
        }
    }

    private Long create(Role role) {
        RoleEntity roleEntity = roleMapper.toEntity(role);

        RoleEntity savedEntity = roleRepository.save(roleEntity);
        return savedEntity.getId();
    }
}
