package com.pangtrue.practice.application.management.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.apache.ibatis.type.Alias;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 3. 12.
 * Time: 오후 11:20
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Alias(value = "au_member")
public class Member {

    private String id;
    private String pw;
    private String email;
    private String name;
    private String regDate;
    private Integer auth;
    private String groupID;
    private String groupName;
}
