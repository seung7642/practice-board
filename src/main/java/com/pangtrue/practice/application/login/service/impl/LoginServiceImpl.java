package com.pangtrue.practice.application.login.service.impl;

import com.pangtrue.practice.application.login.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 3. 12.
 * Time: 오후 9:18
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public String login(String id, String pw) {

        return "";
    }

    @Override
    public void logout() {

    }

    @Override
    public boolean isLogin() {

        return false;
    }
}
