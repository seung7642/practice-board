package com.pangtrue.practice.application.web.user;

import com.pangtrue.practice.application.domain.user.Member;
import com.pangtrue.practice.application.service.user.MemberService;
import com.pangtrue.practice.commons.constants.RETURN_TP;
import com.pangtrue.practice.commons.exception.MessageException;
import com.pangtrue.practice.infrastructure.entity.ResponseBase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 3. 12.
 * Time: 오후 11:35
 */
@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping(value = "/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/list")
    @ResponseBody
    public ResponseBase memberList() {
        try {
            List<Member> memberList = memberService.getMemberList();
            return ResponseBase.of(RETURN_TP.OK, "", memberList);
        } catch (MessageException ex) {
            return ResponseBase.of(RETURN_TP.FAIL, ex.getMessage(), null);
        } catch (Exception ex) {
            return ResponseBase.of(RETURN_TP.FAIL, ex.getMessage(), null);
        }
    }

    @PostMapping("/signUp")
    @ResponseBody
    public ResponseBase signUp(@RequestBody MemberRequest request) {
        try {
            if (memberService.signUp(request)) {
                return ResponseBase.of(RETURN_TP.OK, "Sign up succeeded", null);
            } else {
                return ResponseBase.of(RETURN_TP.FAIL, "Sign up failed", null);
            }
        } catch (MessageException ex) {
            return ResponseBase.of(RETURN_TP.FAIL, ex.getMessage(), null);
        } catch (Exception ex) {
            return ResponseBase.of(RETURN_TP.FAIL, ex.getMessage(), null);
        }
    }
}
