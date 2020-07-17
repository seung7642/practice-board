package com.pangtrue.practice.application.board.web.dto;

import com.pangtrue.practice.application.board.domain.Board;
import lombok.Getter;

@Getter
public class BoardResponse {

    private Long idx;
    private String title;
    private String content;
    private String author;

    public BoardResponse(Board entity) {
        this.idx = entity.getIdx();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
