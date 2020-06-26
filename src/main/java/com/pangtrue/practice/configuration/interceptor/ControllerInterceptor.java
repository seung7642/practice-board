package com.pangtrue.practice.configuration.interceptor;

import com.pangtrue.practice.application.login.service.LoginService;
import com.pangtrue.practice.commons.utils.WebUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * User: Seungho Lee (seung7642@gmail.com)
 * Date: 2020.06.18
 * Time: 17:30
 */
@Slf4j
@AllArgsConstructor
public class ControllerInterceptor extends HandlerInterceptorAdapter {

    private LoginService loginService;

    /**
     * 어떤 경로든 접근했을 때 로그인 여부를 확인하여 로그인이 되어있지 않다면 '/login' 경로로 리다이렉트시킨다.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        boolean isLogin = loginService.isLogin();
        if (!isLogin) {
            String redirectUrl = "/login?rUrl=" + URLEncoder.encode(WebUtils.getRequestServerUri(request), StandardCharsets.UTF_8.name());
            response.sendRedirect(redirectUrl);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
