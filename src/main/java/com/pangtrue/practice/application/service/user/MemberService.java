package com.pangtrue.practice.application.service.user;

import com.pangtrue.practice.application.domain.user.Member;
import com.pangtrue.practice.application.domain.user.MemberRepository;
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
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordSafetyChecker passwordSafetyChecker;

    public List<Member> getMemberList() {
        return memberRepository.findAll();
    }

    public boolean signUp(MemberRequest request) {
        PreconditionUtils.invalidCondition(request == null, "There is no registration information.");
        PreconditionUtils.invalidCondition(StringUtils.isBlank(request.getId()), "ID is not valid.");
        PreconditionUtils.invalidCondition(StringUtils.isBlank(request.getPw()), "Password is not valid.");
        PreconditionUtils.invalidCondition(StringUtils.isBlank(request.getEmail()), "Email is not valid.");



        return false;
    }

    public boolean isEnabledId(String id) {
        return false;
    }

    public boolean deleteMember(String id) {
        return false;
    }

    public boolean changeAccount(String id, String currentPassword, String newName, String newEmail, String auth) {
        return false;
    }

    public boolean changePassword(String id, String currentPassword, String newPassword) {
        return false;
    }
}
