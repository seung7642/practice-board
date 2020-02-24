package com.pangtrue.practice.application.board.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class BoardControllerTest {

    @Autowired
    private MockMvc mvc;

    // BoardController에 있는 list() 메서드를 테스트하려면 메서드 파라미터까지 지정해줘야하나 ?
    @Test
    public void list(ModelAndView mnv,
                     @PageableDefault(page = 1, size = 10) Pageable pageable) throws Exception {
        mvc.perform(get("/board/list"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content());
    }
}
