package com.pangtrue.practice.application.board.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.apache.ibatis.type.Alias;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 1. 10.
 * Time: 오후 8:18
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Alias("Criteria")
public class Criteria {

    private Integer pageNum;
    private Integer amount;
    private Integer startRowNumber;

    public Criteria() {
        this(1, 10); // 페이지 번호 1, 1개의 페이지당 게시글 수는 10개로 설정
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;

        startRowNumber = (pageNum - 1) * amount;
    }
}
