package com.pangtrue.practice.application.login.service;

import com.pangtrue.practice.application.management.domain.Member;
import com.pangtrue.practice.application.management.domain.MemberRepository;
import com.pangtrue.practice.application.management.web.dto.MemberRequest;
import com.pangtrue.practice.commons.utils.PreconditionUtils;
import com.pangtrue.practice.commons.utils.XssFilterUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Optional;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 3. 12.
 * Time: 오후 9:18
 */
@Slf4j
@AllArgsConstructor
@Service
public class LoginService implements HttpSessionBindingListener {

    private static final String SESSION_NAME_MEMBER = "MEMBER";
    private static Hashtable loginUsers = new Hashtable();
    private MemberRepository memberRepository;
    private HttpServletRequest request;

    public String login(String id, String pw) {
        PreconditionUtils.invalidCondition(StringUtils.isEmpty(id), "Please enter ID.");
        PreconditionUtils.invalidCondition(StringUtils.isEmpty(pw), "Please enter PW.");
        String result = "";

        // TODO : 어느 단에서 id, pw를 escape해서 입력받을까?
        if (XssFilterUtils.isOn()) {
            id = XssFilterUtils.unescape(id);
            pw = XssFilterUtils.unescape(pw);
        }

        Member member = memberRepository.findById(id).get();
        PreconditionUtils.invalidCondition(member == null, "You have entered the wrong user name or password. Please check again.");
        PreconditionUtils.invalidCondition(!BCrypt.checkpw(pw, member.getPw()), "You have entered the wrong user name or password. Please check again.");

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

    public void logout() {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
    }

    public boolean isLogin() {
        Member member = getMember();
        if (member == null) {
            return false;
        }

        return !StringUtils.isEmpty(member.getId());
    }

    public Member getMember() {
        HttpSession session = request.getSession();
        if (session == null) {
            return null;
        }

        return (Member) session.getAttribute(SESSION_NAME_MEMBER);
    }

    public boolean isUsing(String userId) {
        return loginUsers.containsValue(userId);
    }

    public boolean findPassword(String id, String email) {
        return false;
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
//        member.setPw("");

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

    /**
     * 세션이 연결될 때 Hashtable에 접속자를 저장한다. (해당 메서드는 세션이 연결될 때 자동으로 호출된다.)
     * @param event
     */
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        loginUsers.put(event.getSession(), event.getName());
    }

    /**
     * 세션이 끊기면 Hashtable에 있는 접속자 정보를 제거한다. (해당 메서드는 세션이 끊길 때 자동으로 호출된다.)
     * @param event
     */
    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        loginUsers.remove(event.getSession());
    }
}
