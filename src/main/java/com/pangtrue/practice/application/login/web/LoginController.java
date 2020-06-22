package com.pangtrue.practice.application.login.web;

import com.pangtrue.practice.application.login.service.LoginService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 3. 12.
 * Time: 오후 9:18
 */
@Slf4j
@AllArgsConstructor
@Controller
public class LoginController {

    private final LoginService loginService;

    @GetMapping(value = "/login")
    @ResponseBody
    public ResponseEntity login(ModelAndView mnv) {

        return ResponseEntity.of(null);
    }
}
