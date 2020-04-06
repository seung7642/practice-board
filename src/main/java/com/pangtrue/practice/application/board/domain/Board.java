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
@Alias("Board")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Board {

    int idx;

    @NotBlank(message = "Title is mandatory !")
    @Length(max = 1024)
    String title;

    @NotBlank(message = "Content is mandatory !")
    @Length(max = 1024 * 1024)
    String content;

    String writer;
    Date regDate;
    Date updDate;
    int hits;
}
