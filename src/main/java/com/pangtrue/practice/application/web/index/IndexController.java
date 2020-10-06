package com.pangtrue.practice.application.web.index;

import com.pangtrue.practice.application.service.user.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final LoginService loginService;

    @GetMapping(value = { "/", "/main" })
    public ModelAndView main(ModelAndView mnv) {
        if (loginService.isLogin()) {
            mnv.setViewName("index/main");
        } else {
            mnv.setViewName("login/main");
        }
        return mnv;
    }
}
