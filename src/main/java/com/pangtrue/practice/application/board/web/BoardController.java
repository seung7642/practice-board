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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 1. 10.
 * Time: 오후 8:18
 */
@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity list(@PageableDefault(page = 1, size = 10) Pageable pageable) {
        return ResponseEntity.of(Optional.of(boardService.getArticleList(pageable)));
    }

    @GetMapping("/write")
    @ResponseBody
    public ResponseEntity write() {
        return ResponseEntity.of(null);
    }

    @PostMapping("/write")
    @ResponseBody
    public ResponseEntity write(@Valid @RequestBody Board board) {
        return ResponseEntity.of(Optional.of(boardService.getArticle(boardService.insertArticle(board))));
    }

    @GetMapping("/read")
    @ResponseBody
    public ResponseEntity read(@RequestParam("idx") Integer idx) {
        boardService.updateHits(idx);
        return ResponseEntity.of(Optional.of(boardService.getArticle(idx)));
    }

    @DeleteMapping("delete")
    @ResponseBody
    public ResponseEntity delete(@RequestParam("idx") Integer idx) {
        return ResponseEntity.of(Optional.of(boardService.deleteArticle(idx)));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotValidException.class)
    public ResponseEntity handleNotValidExceptions(NotValidException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.of(null);
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleExceptions(Exception ex) {
        log.error(ex.getMessage());
        return ResponseEntity.of(null);
    }
}
