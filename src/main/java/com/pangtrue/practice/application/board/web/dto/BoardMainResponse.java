package com.pangtrue.practice.application.board.web.dto;

import com.pangtrue.practice.application.board.domain.Board;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
public class BoardMainResponse {

    private Long id;
    private String title;
    private String author;
    private String modifiedDate;

    public BoardMainResponse(Board entity) {
        id = entity.getIdx();
        title = entity.getTitle();
        author = entity.getAuthor();
        modifiedDate = toStringDateTime(entity.getModifiedDate());
    }

    private String toStringDateTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }
}
