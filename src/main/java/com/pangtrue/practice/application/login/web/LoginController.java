package com.pangtrue.practice.application.login.web;

import com.pangtrue.practice.application.login.service.LoginService;
import com.pangtrue.practice.commons.constants.RETURN_TP;
import com.pangtrue.practice.infrastructure.entity.ResponseBase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    public String loginGET(@RequestParam(value = "rUrl", required = false, defaultValue = "") String rUrl) {
        if (loginService.isLogin()) {
            loginService.logout();
        }

        return "login/main";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseBase loginPOST(@RequestParam(value = "id", defaultValue = "") String id,
                                  @RequestParam(value = "pw", defaultValue = "") String pw,
                                  @RequestParam(value = "rUrl", defaultValue = "") String rUrl) {
        try {
            String result = loginService.login(id, pw);
            if ("true".equals(result)) {
                return ResponseBase.of(RETURN_TP.OK, "", true);
            } else if ("isUsing".equals(result)) {
                return ResponseBase.of(RETURN_TP.OK, "This ID is already taken.", true);
            } else {
                return ResponseBase.of(RETURN_TP.FAIL, "Login failed.", false);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return ResponseBase.of(RETURN_TP.FAIL, "login failed", false);
        }
    }

    @GetMapping("/logout")
    public String logout() {
        loginService.logout();
        return "redirect:/main";
    }

    @PostMapping("/login/findPassword")
    @ResponseBody
    public ResponseBase findPassword(@RequestParam(value = "findId") String findId,
                                     @RequestParam(value = "findEmail") String findEmail) {
        try {
            // TODO: 패스워드 찾기 로직 마무리하기.
            loginService.findPassword(findId, findEmail);

            return ResponseBase.of(RETURN_TP.OK, "", true);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return ResponseBase.of(RETURN_TP.FAIL, "", false);
        }
    }

    @PostMapping("/loginCheck")
    @ResponseBody
    public ResponseBase loginCheck() {
        if (loginService.isLogin()) {
            return ResponseBase.of(RETURN_TP.OK, "login", true);
        } else {
            return ResponseBase.of(RETURN_TP.FAIL, "Not login", false);
        }
    }
}
