package com.neowiz.practice.board.application.index.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public ModelAndView index(ModelAndView mnv) {
        // mnv.addObject("message", Message);
        mnv.setViewName("index");
        return mnv;
    }
}
