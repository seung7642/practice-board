package com.pangtrue.practice.application.domain.posts;

import lombok.*;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 1. 10.
 * Time: 오후 8:18
 *
 * 페이지 목록을 처리하기 위한 클래스.
 * 페이지 목록 형식 : << 처음 이전 1 2 3 4 5 6 7 8 9 10 다음 마지막 >>
 */
@Getter
@Setter
@ToString
public class PageMaker<T> extends PageImpl<T> {

    private static final int DISPLAY_PAGE_NUM = 5;  // << 1, 2, 3, 4, 5 >>  페이지 목록에 몇 개씩 표시할건지 나타낼 변수

    private int totalArticleCount;
    private int startPage; // 현재 페이지 목록에서 시작 페이지 번호 << 1 2 3 4 5 >> 라고 한다면 startPage = 1
    private int endPage;   // 현재 페이지 목록에서 끝 페이지 번호 << 1 2 3 4 5 >> 라고 한다면 endPage = 5

    // [이전], [다음] 탭을 위한 필드.
    private int firstPageListNum;
    private int lastPageListNum;
    private int currentPageListNum;
    private int prevPageListNum;
    private int nextPageListNum;
    private int prevPage;
    private int nextPage;

    // PageImpl 클래스를 상속받게되면 TotalElements의 수는 내장되어 있다.
    public PageMaker(List<T> content, Pageable pageable, int totalArticleCount) {
        super(content, pageable, totalArticleCount);

        this.totalArticleCount = totalArticleCount;
        endPage = (int) (Math.ceil(getPageable().getPageNumber() / (double) DISPLAY_PAGE_NUM) * DISPLAY_PAGE_NUM);
        startPage = (endPage - DISPLAY_PAGE_NUM) + 1;

        int lastPage = (int) (Math.ceil(totalArticleCount / (double) getSize()));
        if (endPage > lastPage) endPage = lastPage;

        setPrevNext();
    }

    /**
     * [이전] [다음] 탭을 구하기 위한 메서드
     *
     * @return void
     */
    private void setPrevNext() {
        // 첫 번째 페이지그룹 번호와 마지막 페이지그룹 번호, 지금 보고있는 페이지의 페이지그룹 번호.
        firstPageListNum = 1;
        lastPageListNum = (int) Math.ceil(getTotalPages() / (double) DISPLAY_PAGE_NUM);
        currentPageListNum = (int) Math.ceil(getPageable().getPageNumber() / (double) DISPLAY_PAGE_NUM);

        // [이전] 탭을 눌렀을 때의 페이지 목록 값 && 페이지번호.
        if (currentPageListNum > firstPageListNum) {
            prevPageListNum = currentPageListNum - 1;
            prevPage = (prevPageListNum - 1) * DISPLAY_PAGE_NUM + 1;
        }
        else {
            prevPageListNum = currentPageListNum;
            prevPage = 1;
        }

        // [다음] 탭을 눌렀을 때의 페이지 목록 값 && 페이지번호.
        if (currentPageListNum < lastPageListNum) {
            nextPageListNum = currentPageListNum + 1;
            nextPage = (nextPageListNum - 1) * DISPLAY_PAGE_NUM + 1;
        }
        else {
            nextPageListNum = currentPageListNum;
            nextPage = getTotalPages();
        }
    }
}

