package com.pangtrue.practice.application.login.service;

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
}
