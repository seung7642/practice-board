package com.pangtrue.practice.application.management.web;

import com.pangtrue.practice.application.management.domain.Member;
import com.pangtrue.practice.application.management.service.MemberService;
import com.pangtrue.practice.commons.exception.MessageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 3. 12.
 * Time: 오후 11:35
 */
@Slf4j
@RestController
@RequestMapping(value = "/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(value = "/list")
    public ResponseEntity<List<Member>> memberList() {
        ResponseEntity<List<Member>> entity = null;

        try {
            List<Member> list = memberService.getMemberList();
            entity = new ResponseEntity<>(list, HttpStatus.OK);
        } catch (MessageException e) {
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }
}
