package com.practice.board.application.board.domain;

import lombok.Data;

@Data
public class AttachFile {

    private String fileName;
    private String uploadPath;
    private String uuid;
    private boolean image;
}
