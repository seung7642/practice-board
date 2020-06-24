package com.pangtrue.practice.application.board.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 1. 10.
 * Time: 오후 8:18
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Alias("Board")
public class Board {

    private Integer idx;

    @NotBlank(message = "Title is mandatory !")
    @Length(max = 1024)
    private String title;

    @NotBlank(message = "Content is mandatory !")
    @Length(max = 1024 * 1024)
    private String content;

    private String writer;
    private Date regDate;
    private Date updDate;
    private Integer hits;
}
