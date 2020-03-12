package com.pangtrue.practice.application.management.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 3. 12.
 * Time: 오후 11:20
 */
@Alias(value = "au_member")
@Data
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
