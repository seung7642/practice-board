package com.pangtrue.practice.application.board.web;

import com.pangtrue.practice.application.board.service.BoardService;
import com.pangtrue.practice.commons.exception.NotValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
    public ModelAndView list(ModelAndView mnv, @PageableDefault(page = 1, size = 10) Pageable pageable) {
        log.debug("요청으로 들어온 pageNumber = {}, pageSize = {}", pageable.getPageNumber(), pageable.getPageSize());

        mnv.addObject("boardList", boardService.getArticleList(pageable));
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

    // Service단에서 유효성 검증 실패가 발생하면 Spring 컨테이너쪽으로 익셉션을 던진다.
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotValidException.class)
    public void handleNotValidExceptions(NotValidException ex) throws Exception {
        log.error(ex.getMessage());
        throw new Exception();
    }
}
