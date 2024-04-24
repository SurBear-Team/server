package com.surbear.member.entity;

import com.surbear.common.entity.BaseTimeEntity;
import com.surbear.member.constant.Permission;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "roles")
public class RoleEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Long id;

    @Column
    Long memberId;

    @Column
    @Enumerated(EnumType.STRING)
    private Permission permission;

    @Builder.Default
    @Column
    boolean deleted = false;

    public void delete() {
        deleted = true;
    }

}
