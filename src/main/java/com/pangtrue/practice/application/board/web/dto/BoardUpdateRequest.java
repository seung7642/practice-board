package com.pangtrue.practice.application.board.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardUpdateRequest {

    String title;
    String content;

    @Builder
    public BoardUpdateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
