package com.neowiz.practice.board.application.board.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Board {
    private int idx;
    private String title;
    private String content;
    private String writer;
    private Date regDate;
    private Date updDate;
    private int hits;
}
