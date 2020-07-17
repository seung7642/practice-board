package com.pangtrue.practice.application.board.web.dto;

import com.pangtrue.practice.application.board.domain.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardSaveRequest {

    private String title;
    private String content;
    private String author;

    @Builder
    public BoardSaveRequest(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
