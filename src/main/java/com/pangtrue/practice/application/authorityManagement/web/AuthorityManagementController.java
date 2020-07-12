package com.pangtrue.practice.application.authorityManagement.web;

import com.pangtrue.practice.application.authorityManagement.service.AuthorityManagementService;
import com.pangtrue.practice.commons.constants.RETURN_TP;
import com.pangtrue.practice.infrastructure.entity.ResponseBase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: Seungho Lee (seung7642@gmail.com)
 * Date: 2020.07.10
 * Time: 18:35
 */
@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/authority")
public class AuthorityManagementController {

//    private final AuthorityManagementService authorityManagementService;

    @PostMapping("/createGroup")
    @ResponseBody
    public ResponseBase createGroup() {

        return ResponseBase.of(RETURN_TP.OK, "Create group successful.", true);
    }
}
