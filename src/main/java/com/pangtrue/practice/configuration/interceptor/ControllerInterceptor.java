package com.pangtrue.practice.configuration.interceptor;

import com.pangtrue.practice.application.login.service.LoginService;
import com.pangtrue.practice.commons.utils.WebUtils;
import com.pangtrue.practice.infrastructure.annotation.LoginNotRequired;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.Nullable;
import org.springframework.ui.ModelMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

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
@NoArgsConstructor
@AllArgsConstructor
public class ControllerInterceptor extends HandlerInterceptorAdapter {

    private LoginService loginService;

    /**
     * 어떤 경로든 접근했을 때 로그인 여부를 확인하여 로그인이 되어있지 않다면 '/login' 경로로 리다이렉트시킨다.
     * @param request
     * @param response
     * @param handler
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        if (isLoginNotRequired(handler)) {
//            return true;
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
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return;
        }

    }

    private boolean isLoginNotRequired(Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LoginNotRequired loginNotRequired = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), LoginNotRequired.class);

        return (loginNotRequired != null);
    }

    private boolean isAvableServiceVariable(ModelAndView modelAndView) {
        if (modelAndView != null
                && !(modelAndView.getView() instanceof RedirectView)
                && !(modelAndView.getViewName() == null)
                && !modelAndView.getViewName().startsWith("redirect:")
                && !modelAndView.getViewName().startsWith("check/live")) {
            return true;
        }

        return false;
    }

    private ModelMap getServiceVariables(HttpServletRequest request) {
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("MEMBER", loginService.getMember());

        return modelMap;
    }
}
