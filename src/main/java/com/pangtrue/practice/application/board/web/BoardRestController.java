package com.pangtrue.practice.application.board.web;

import com.pangtrue.practice.application.board.domain.Board;
import com.pangtrue.practice.application.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 1. 10.
 * Time: 오후 8:18
 */
@Slf4j
@RestController
@RequestMapping(value = "/board")
public class BoardRestController {

    private final BoardService boardService;

    @Autowired
    public BoardRestController(BoardService boardService) { this.boardService = boardService; }

    // 유효성 검증에 실패하면 Spring Boot는 MethodArgumentNotValidException 예외를 던진다.
    @PostMapping(value = "/write")
    public Board write(@Valid @RequestBody Board board, BindingResult bindingResult) {
        log.info("JSON으로 넘어온 데이터 : {}", board.toString());
        return boardService.getArticle(boardService.insertArticle(board));
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
