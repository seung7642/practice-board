package com.pangtrue.practice.application.management.service;

import com.pangtrue.practice.application.management.domain.Member;

import java.util.List;

public interface MemberService {

    /**
     * 멤버 리스트를 반환한다.
     * @return List<Member>
     */
    List<Member> getMemberList();
}
