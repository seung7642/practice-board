package com.pangtrue.practice.application.web.user.dto;

import com.pangtrue.practice.application.domain.user.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberRequest {

    private String id;
    private String pw;
    private String email;
    private String name;

    @Builder
    public MemberRequest(String id, String pw, String email, String name) {
        this.id = id;
        this.pw = pw;
        this.email = email;
        this.name = name;
    }

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .pw(pw)
                .email(email)
                .name(name)
                .build();
    }
}
