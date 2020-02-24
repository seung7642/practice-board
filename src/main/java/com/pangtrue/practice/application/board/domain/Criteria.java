package com.pangtrue.practice.application.board.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

// 페이징 처리를 위해서는 페이지 번호와 1페이지당 몇 개의 게시글을 보여줄건지 설정이 필요하다.
// 이것을 위한 도메인 객체. Criteria는 '검색의 기준'이라는 의미.
@Alias("Criteria")
@Data
public class Criteria {

    private int pageNum;
    private int amount;
    private int startRowNumber;

    public Criteria() {
        this(1, 10); // 페이지 번호 1, 1개의 페이지당 게시글 수는 10개로 설정
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;

        startRowNumber = (pageNum - 1) * amount;
    }
}
