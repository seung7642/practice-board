package com.pangtrue.practice.application.management.web;

import com.pangtrue.practice.application.management.domain.Member;
import com.pangtrue.practice.application.management.service.MemberService;
import com.pangtrue.practice.commons.exception.MessageException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity memberList() {
        try {
            return ResponseEntity.of(Optional.of(memberService.getMemberList()));
        } catch (MessageException e) {
            return ResponseEntity.of(null);
        } catch (Exception e) {
            return ResponseEntity.of(null);
        }
    }
}
