package com.pangtrue.practice.application.management.service.impl;

import com.pangtrue.practice.application.management.dao.MemberDao;
import com.pangtrue.practice.application.management.domain.Member;
import com.pangtrue.practice.application.management.service.MemberService;
import com.pangtrue.practice.commons.utils.PreconditionUtils;
import com.pangtrue.practice.infrastructure.password.PasswordSafetyChecker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

    private final MemberDao memberDao;
    private final PasswordSafetyChecker passwordSafetyChecker;

    @Override
    public List<Member> getMemberList() {
        return memberDao.getMemberList();
    }

    @Override
    public boolean signUp(Member member) {
        PreconditionUtils.invalidCondition(member == null, "There is no registration information.");
        PreconditionUtils.invalidCondition(StringUtils.isBlank(member.getId()), "ID is not valid.");
        PreconditionUtils.invalidCondition(StringUtils.isBlank(member.getPw()), "Password is not valid.");
        PreconditionUtils.invalidCondition(StringUtils.isBlank(member.getEmail()), "Email is not valid.");



        return false;
    }

    @Override
    public boolean isEnabledId(String id) {
        return false;
    }

    @Override
    public boolean deleteMember(String id) {
        return false;
    }

    @Override
    public boolean changeAccount(String id, String currentPassword, String newName, String newEmail, String auth) {
        return false;
    }

    @Override
    public boolean changePassword(String id, String currentPassword, String newPassword) {
        return false;
    }
}
