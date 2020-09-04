package com.pangtrue.practice.application.domain.user;

import com.pangtrue.practice.infrastructure.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 3. 12.
 * Time: 오후 11:20
 */
@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private String pw;

    private Integer auth;

    private String groupName;

    @Builder
    public Member(String id, String pw, String email, String name) {
        this.id = id;
        this.pw = pw;
        this.email = email;
        this.name = name;
    }

    public Member update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleType() {
        return this.role.getKey();
    }
}
