package com.pangtrue.practice.application.management.domain;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.apache.ibatis.type.Alias;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 3. 12.
 * Time: 오후 11:20
 */
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Member {

    @Id
    private String id;

    private String pw;

    private String email;

    private String name;

    private String regDate;

    private Integer auth;

    private String groupName;

    @Builder
    public Member(String id, String pw, String email, String name, String regDate, Integer auth, String groupName) {
        this.id = id;
        this.pw = pw;
        this.email = email;
        this.name = name;
        this.regDate = regDate;
        this.auth = auth;
        this.groupName = groupName;
    }
}
