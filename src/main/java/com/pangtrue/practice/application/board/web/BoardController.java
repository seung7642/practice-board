package com.pangtrue.practice.application.board.web;

import com.pangtrue.practice.application.board.domain.Board;
import com.pangtrue.practice.application.board.service.BoardService;
import com.pangtrue.practice.commons.exception.NotValidException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 1. 10.
 * Time: 오후 8:18
 */
@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping(value = "/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping(value = "/list")
    @ResponseBody
    public ModelAndView list(ModelAndView mnv, @PageableDefault(page = 1, size = 10) Pageable pageable) {
        log.debug("요청으로 들어온 pageNumber = {}, pageSize = {}", pageable.getPageNumber(), pageable.getPageSize());

        mnv.addObject("boardList", boardService.getArticleList(pageable));
        mnv.setViewName("board/list");
        return mnv;
    }

    @GetMapping(value = "/write")
    @ResponseBody
    public ModelAndView write(ModelAndView mnv) {
        mnv.setViewName("board/write");
        return mnv;
    }

    @PostMapping(value = "/write")
    @ResponseBody
    public Board write(@Valid @RequestBody Board board, BindingResult bindingResult) {
        log.info("JSON으로 넘어온 데이터 : {}", board.toString());
        return boardService.getArticle(boardService.insertArticle(board));
    }

    @GetMapping(value = "/read")
    @ResponseBody
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotValidException.class)
    public void handleNotValidExceptions(NotValidException ex) throws Exception {
        log.error(ex.getMessage());
        throw new Exception();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            errors.put(fieldName, errorMessage); // [ KEY : 유효성 검증에 실패한 필드, VALUE : 에러 메시지 ]
        });

        return errors;
    }
}
