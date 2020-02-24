package com.pangtrue.practice.application.board.domain;

import lombok.Data;
import lombok.Getter;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Alias("Board")
@Data
public class Board {
    private int idx;
    @NotBlank(message = "Title is mandatory !")
    @Length(max = 1024)
    private String title;
    @NotBlank(message = "Content is mandatory !")
    @Length(max = 1024 * 1024)
    private String content;
    private String writer;
    private Date regDate;
    private Date updDate;
    private int hits;
}
