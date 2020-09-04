package com.pangtrue.practice.application.web.posts.dto;

import com.pangtrue.practice.application.domain.posts.BoardAttach;
import lombok.Getter;

@Getter
public class BoardAttachMainResponse {

    private String uuid;
    private String fileName;
    private Long idx;

    public BoardAttachMainResponse(BoardAttach entity) {
        uuid = entity.getUuid();
        fileName = entity.getFileName();
        idx = entity.getIdx();
    }
}
