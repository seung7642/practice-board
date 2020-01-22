package com.practice.board.application.board.web;

import com.practice.board.application.board.domain.Board;
import com.practice.board.application.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/board")
public class BoardRestController {

    private final BoardService boardService;

    @Autowired
    public BoardRestController(BoardService boardService) { this.boardService = boardService; }

    @PostMapping(value = "/write")
    public String write(@RequestBody Board board) {

        return "redirect:/board/list";
    }
}
