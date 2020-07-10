package com.pangtrue.practice.application.management.service;

import com.pangtrue.practice.application.management.domain.Member;

import java.util.List;

public interface MemberService {

    /**
     * 멤버 리스트를 반환한다.
     * @return List<Member>
     */
    List<Member> getMemberList();

    /**
     * 회원가입을 진행한다.
     * @param member
     * @return boolean
     */
    boolean signUp(Member member);

    /**
     * 회원가입 요청에 대해 id가 중복되는지를 확인한다.
     * @param id
     * @return boolean
     */
    boolean isEnabledId(String id);

    /**
     * id를 삭제한다.
     * @param id
     * @return boolean
     */
    boolean deleteMember(String id);

    /**
     * 해당 계정의 정보를 변경한다. (모든 정보에 대한 변경)
     * @param id
     * @param currentPassword
     * @param newName
     * @param newEmail
     * @param auth
     * @return boolean
     */
    boolean changeAccount(String id, String currentPassword, String newName, String newEmail, String auth);

    /**
     * 해당 계정의 패스워드를 변경한다.
     * @param id
     * @param currentPassword
     * @param newPassword
     * @return boolean
     */
    boolean changePassword(String id, String currentPassword, String newPassword);
}
