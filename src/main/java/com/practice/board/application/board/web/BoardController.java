package com.practice.board.application.board.web;

import com.practice.board.application.board.domain.Board;
import com.practice.board.application.board.domain.Criteria;
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

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) { this.boardService = boardService; }

    @GetMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum
                           , @RequestParam(value = "amount", defaultValue = "10") int amount, ModelAndView mnv) {
        log.info("쿼리 스트링으로 넘어온 값 : [ {}, {} ]", pageNum, amount);

        Criteria criteria = new Criteria(pageNum, amount);
        try {
            mnv.addObject("pageMaker", boardService.getPageMaker(criteria));
            mnv.addObject("boardList", boardService.getArticleList());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mnv.setViewName("board/list");
        return mnv;
    }

    @GetMapping(value = "/write")
    public ModelAndView write(ModelAndView mnv) {
        mnv.setViewName("board/write");
        return mnv;
    }

    @GetMapping(value = "/read")
    public ModelAndView read(@RequestParam("idx") Integer idx, ModelAndView mnv) {
        log.info("읽고자하는 글 번호 : {}", idx);

        try {
            boardService.updateHits(idx);
            mnv.addObject("article", boardService.getArticle(idx));
        } catch (Exception e) {
            e.printStackTrace();
        }

        mnv.setViewName("board/read");
        return mnv;
    }
}
