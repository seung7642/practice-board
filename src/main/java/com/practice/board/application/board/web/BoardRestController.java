package com.practice.board.application.board.web;

import com.practice.board.application.board.domain.Board;
import com.practice.board.application.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/board")
public class BoardRestController {

    private static final Logger log = LoggerFactory.getLogger(BoardRestController.class);
    private final BoardService boardService;

    @Autowired
    public BoardRestController(BoardService boardService) { this.boardService = boardService; }

    // 유효성 검증에 실패하면 Spring Boot는 MethodArgumentNotValidException 예외를 던진다.
    @PostMapping(value = "/write")
    public Map<String, Integer> write(@Valid @RequestBody Board board, BindingResult errors) {
        log.info("JSON으로 넘어온 데이터 : {}", board.toString());

        // IF @Valid를 통과하지 못했다면:
        //     error 페이지로 리다이렉트 + 에러 메시지 전달
        if (errors.hasErrors()) {
            log.warn("유효성 검사 실패 !");

            List<ObjectError> list = errors.getAllErrors();
            for( ObjectError error : list )
                log.warn("{}", error);
        }

        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            map.put("idx", boardService.insertArticle(board));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map; // REST Controller는 반환값이 json 타입이어야한다. 아니면 ajax에서 error로 드감
    }

    @PostMapping(value = "/upload")
    public String upload(@RequestBody MultipartFile multipartFile) {

        // TODO : 파일 업로드를 위한 Service단의 메서드

        return "";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getAllErrors()
                .forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            errors.put(fieldName, errorMessage); // [ KEY : 유효성 검증에 실패한 필드, VALUE : 에러 메시지 ]
        });

        return errors;
    }
}
