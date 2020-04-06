package com.pangtrue.practice.application.management.dao;

import com.pangtrue.practice.application.management.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 3. 12.
 * Time: 오후 11:50
 */
@Repository
@Mapper
public interface MemberMapper {

    List<Member> getMemberList();

    Member getMember(String id);

    Integer insertMember(Member member, String regdate);

    Integer deleteMember(String id);

    /**
     * 계정 변경 (패스워드, 메일, 권한)
     *
     * @param id
     * @param newName
     * @param newEmail
     * @param auth
     * @return Integer
     */
    Integer changeAccount(String id, String newName, String newEmail, String auth);

    /**
     * 비밀번호 변경
     *
     * @param id
     * @param newPassword
     * @return Integer
     */
    Integer changePassword(String id, String newPassword);

    /**
     * 멤버 권한 변경
     *
     * @param id
     * @param newAuth
     * @return Integer
     */
    Integer changeAuth(String id, Integer newAuth);

    /**
     * 멤버 메일 변경
     *
     * @param id
     * @param newEmail
     * @return Integer
     */
    Integer changeEmail(String id, String newEmail);

    Member checkEmail(String id, String email);
}
