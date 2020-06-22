package com.pangtrue.practice.application.management.service.impl;

import com.pangtrue.practice.application.management.dao.MemberMapper;
import com.pangtrue.practice.application.management.domain.Member;
import com.pangtrue.practice.application.management.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 3. 12.
 * Time: 오후 11:35
 */
@Slf4j
@AllArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Override
    public List<Member> getMemberList() {
        return memberMapper.getMemberList();
    }
}
