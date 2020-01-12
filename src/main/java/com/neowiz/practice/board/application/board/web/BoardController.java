package com.neowiz.practice.board.application.board.web;

import com.neowiz.practice.board.application.board.domain.Board;
import com.neowiz.practice.board.application.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BoardController {

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) { this.boardService = boardService; }

    @GetMapping(value = "/")
    public ModelAndView list(ModelAndView mnv) {
        List<Board> boardList = boardService.getArticleList();

        mnv.addObject("boardList", boardList);
        mnv.setViewName("/board/list");
        return mnv;
    }
}
