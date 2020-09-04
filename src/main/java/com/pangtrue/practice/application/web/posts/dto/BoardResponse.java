package com.pangtrue.practice.application.web.posts.dto;

import com.pangtrue.practice.application.domain.posts.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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
