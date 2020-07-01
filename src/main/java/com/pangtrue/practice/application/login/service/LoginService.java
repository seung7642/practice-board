package com.pangtrue.practice.application.login.service;

import com.pangtrue.practice.application.management.domain.Member;

public interface LoginService {

    /**
     * 로그인을 진행한다.
     * 정상적으로 로그인이 되었다면, 세션에 값을 등록한다.
     * @param id String
     * @param pw String
     * @return String
     */
    String login(String id, String pw);

    /**
     * 세션에 등록된 값을 삭제한다.
     */
    void logout();

    /**
     * 현재 로그인 상태인지 확인한다.
     * @return boolean
     */
    boolean isLogin();

    /**
     * 로그인한 사용자의 회원 정보를 가져온다.
     * @return Member
     */
    Member getMember();

    /**
     * 해당 아이디가 로그인 중인지 확인한다. (동시 사용 방지)
     * @param userId
     * @return
     */
    boolean isUsing(String userId);
}
