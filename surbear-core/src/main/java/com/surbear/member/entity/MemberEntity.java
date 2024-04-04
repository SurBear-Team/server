package com.surbear.member.entity;

import com.surbear.common.entity.BaseTimeEntity;
import com.surbear.member.model.Age;
import com.surbear.member.model.Gender;
import com.surbear.member.model.Role;
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
@Table(name = "members")
public class MemberEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "member_id")
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Age age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Gender gender;

    @Column
    String userId;

    @Column
    String password;

    @Column
    String email;

    @Column
    Integer point;

    @Column
    String nickname;

    @Builder.Default
    @Column
    boolean deleted = false;

}
