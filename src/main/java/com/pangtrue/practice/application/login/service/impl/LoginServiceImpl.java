package com.pangtrue.practice.application.login.service.impl;

import com.pangtrue.practice.application.login.service.LoginService;
import com.pangtrue.practice.application.management.dao.MemberDao;
import com.pangtrue.practice.application.management.domain.Member;
import com.pangtrue.practice.commons.utils.PreconditionUtils;
import com.pangtrue.practice.commons.utils.XssFilterUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 3. 12.
 * Time: 오후 9:18
 */
@Slf4j
@AllArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

    private static final String SESSION_NAME_MEMBER = "MEMBER";
    private static Hashtable loginUsers = new Hashtable();
    private MemberDao memberDao;
    private HttpServletRequest request;

    @Override
    public String login(String id, String pw) {
        PreconditionUtils.invalidCondition(StringUtils.isEmpty(id), "Please enter ID.");
        PreconditionUtils.invalidCondition(StringUtils.isEmpty(pw), "Please enter PW.");
        String result = "";

        // TODO : 어느 단에서 id, pw를 escape해서 입력받을까?
        if (XssFilterUtils.isOn()) {
            id = XssFilterUtils.unescape(id);
            pw = XssFilterUtils.unescape(pw);
        }

        Member member = memberDao.getMember(id);
        PreconditionUtils.invalidCondition(member == null, "You have entered the wrong user name or password. Please check again.");
//        PreconditionUtils.invalidCondition(!BCrypt.checkpw(pw, member.getPw()), "You have entered the wrong user name or password. Please check again.");

        // 중복 로그인 체크
        if (isUsing(id)) {
            result = "isUsing";
            removeSession(id);
        } else {
            result = "true";
        }

        // Menu Auth 등록
        setMenuAuth(member);

        // Session 등록
        setSession(member);

        return result;
    }

    @Override
    public void logout() {

    }

    @Override
    public boolean isLogin() {

        return false;
    }

    @Override
    public Member getMember() {
        return null;
    }

    public boolean isUsing(String userID) {
        return loginUsers.containsValue(userID);
    }

    private HttpSession newSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return request.getSession(true);
    }

    private void setSession(Member member) {
        // 비밀번호 제외
        member.setPw("");

        HttpSession session = newSession(request);
        session.setAttribute(member.getId(), this);
        session.setAttribute(SESSION_NAME_MEMBER, member);
    }

    private void removeSession(String userId) {
        Enumeration e = loginUsers.keys();
        HttpSession session = null;
        while (e.hasMoreElements()) {
            session = (HttpSession) e.nextElement();
            if (loginUsers.get(session).equals(userId)) {
                session.invalidate();
            }
        }
    }

    public void setMenuAuth(Member member) {
        // TODO: Menu Auth 등록
    }
}
