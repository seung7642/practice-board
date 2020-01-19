package com.practice.board.application.board.web;

import com.practice.board.application.board.domain.Board;
import com.practice.board.application.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping(value = "/board")
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) { this.boardService = boardService; }

    @GetMapping(value = "/list")
    public ModelAndView list(ModelAndView mnv) {
        mnv.addObject("total", boardService.getArticleCount());
        mnv.addObject("boardList", boardService.getArticleList());
        mnv.setViewName("/board/list");
        return mnv;
    }

    @GetMapping(value = "/write")
    public ModelAndView write(ModelAndView mnv) {
        mnv.setViewName("/board/write");
        return mnv;
    }

    @ResponseBody
    @PostMapping(value = "/write")
    public String write(@RequestBody Board board) { // @RequestBody와 @RequestParam은 무슨 차이지 ?
        logger.info("JSON으로 넘어온 데이터 : {}", board.toString());
        boardService.insertArticle(board);

        return "redirect:/board/list";
    }
}
