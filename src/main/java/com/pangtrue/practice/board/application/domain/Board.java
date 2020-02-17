package com.pangtrue.practice.board.application.domain;

import lombok.Data;
import lombok.Getter;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Alias("Board")
@Data @Getter
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

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getRegDate() {
        return regDate;
    }

    public int getIdx() {
        return idx;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
