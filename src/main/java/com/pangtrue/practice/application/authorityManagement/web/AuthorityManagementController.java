package com.pangtrue.practice.application.authorityManagement.web;

import com.pangtrue.practice.application.authorityManagement.service.AuthorityManagementService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    private AuthorityManagementService authorityManagementService;
}
