package com.practice.board.application.board.web;

import com.practice.board.application.board.domain.Board;
import com.practice.board.application.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping(value = "/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) { this.boardService = boardService; }

    @GetMapping(value = "/list")
    public ModelAndView list(ModelAndView mnv) {
        mnv.addObject("boardList", boardService.getArticleList());
        mnv.setViewName("/board/list");
        return mnv;
    }

    @GetMapping(value = "/write")
    public ModelAndView write(ModelAndView mnv) {
        mnv.setViewName("/board/write");
        return mnv;
    }

    @PostMapping(value = "/write")
    public String write(@RequestBody Board board) {
        boardService.insertArticle(board);

        return "redirect:/board/list";
    }
}
