package com.neowiz.practice.board.application.board.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Board")
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
