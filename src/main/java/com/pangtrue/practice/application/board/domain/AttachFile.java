package com.pangtrue.practice.application.board.domain;

import lombok.Data;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 1. 10.
 * Time: 오후 8:18
 */
@Data
public class AttachFile {

    private String fileName;
    private String uploadPath;
    private String uuid;
    private boolean image;
}
