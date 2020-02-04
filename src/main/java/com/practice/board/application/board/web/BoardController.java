package com.practice.board.application.board.web;

import com.mysql.cj.log.LogFactory;
import com.practice.board.application.board.domain.Board;
import com.practice.board.application.board.domain.Criteria;
import com.practice.board.application.board.service.BoardService;
import com.practice.board.commons.exception.NotValidException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(BoardController.class);
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) { this.boardService = boardService; }

//    @GetMapping(value = "/list")
//    public ModelAndView list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum
//                           , @RequestParam(value = "amount", defaultValue = "10") int amount, ModelAndView mnv) {
//        log.info("쿼리 스트링으로 넘어온 값 : [ {}, {} ]", pageNum, amount);
//
//        Criteria criteria = new Criteria(pageNum, amount);
//        try {
//            mnv.addObject("pageMaker", boardService.getPageMaker(criteria));
//            mnv.addObject("boardList", boardService.getArticleList(criteria));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        mnv.setViewName("board/list");
//        return mnv;
//    }

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
