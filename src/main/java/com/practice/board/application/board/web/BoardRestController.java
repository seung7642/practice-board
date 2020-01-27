package com.practice.board.application.board.web;

import com.practice.board.application.board.domain.Board;
import com.practice.board.application.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/board")
public class BoardRestController {

    private static final Logger log = LoggerFactory.getLogger(BoardRestController.class);
    private final BoardService boardService;

    @Autowired
    public BoardRestController(BoardService boardService) { this.boardService = boardService; }

    @PostMapping(value = "/write")
    public String write(@Valid @RequestBody Board board, Errors errors) {
        log.info("JSON으로 넘어온 데이터 : {}", board.toString());

        // @Valid를 통과하지 못했다면 error 페이지로 리다이렉트
        if (errors.hasErrors()) {
            return "redirect:/commons/error_500";
        }

        try {
            boardService.insertArticle(board);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/board/list";
    }
}
