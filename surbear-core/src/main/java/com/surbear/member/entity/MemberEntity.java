package com.surbear.member.entity;

import com.surbear.common.entity.BaseTimeEntity;
import com.surbear.member.constant.Age;
import com.surbear.member.constant.Gender;
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
    Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private Age age;

    @Column
    @Enumerated(EnumType.STRING)
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

    public void delete() {
        deleted = true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMemberInformation(Age age, String nickname) {
        this.age = age;
        this.nickname = nickname;
    }
}
