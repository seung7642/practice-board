package com.pangtrue.practice.application.board.web;

import com.pangtrue.practice.application.board.web.dto.BoardSaveRequest;
import com.pangtrue.practice.application.board.service.BoardService;
import com.pangtrue.practice.application.board.web.dto.BoardUpdateRequest;
import com.pangtrue.practice.commons.constants.RETURN_TP;
import com.pangtrue.practice.commons.exception.NotValidException;
import com.pangtrue.practice.infrastructure.entity.ResponseBase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

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
    public ResponseBase list(@PageableDefault(page = 1, size = 10) Pageable pageable) {
        return ResponseBase.of(RETURN_TP.OK, "", boardService.findAllDesc());
    }

    @GetMapping("/write")
    @ResponseBody
    public ResponseEntity write() {
        return ResponseEntity.of(null);
    }

    @PostMapping("/write")
    @ResponseBody
    public ResponseBase write(@Valid @RequestBody BoardSaveRequest request) {
        return ResponseBase.of(RETURN_TP.OK, "", boardService.save(request));
    }

    @PutMapping("/update/{idx}")
    public ResponseBase update(@PathVariable Long idx, @RequestBody BoardUpdateRequest request) {
        return ResponseBase.of(RETURN_TP.OK, "", boardService.update(idx, request));
    }

    @GetMapping("/read/{idx}")
    @ResponseBody
    public ResponseBase read(@PathVariable Long idx) {
        return ResponseBase.of(RETURN_TP.OK, "", boardService.findById(idx));
    }

    @DeleteMapping("/delete/{idx}")
    @ResponseBody
    public ResponseBase delete(@PathVariable Long idx) {
        return ResponseBase.of(RETURN_TP.OK, "", boardService.delete(idx));
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
