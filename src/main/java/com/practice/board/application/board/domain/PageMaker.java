package com.practice.board.application.board.domain;

import lombok.Data;

/**
 *  페이지 목록을 처리하기 위한 클래스.
 *  페이지 목록 형식 : << 처음 이전 1 2 3 4 5 6 7 8 9 10 다음 마지막 >>
 */
@Data
public class PageMaker {

    private int totalArticleCount;    // 총 게시글의 갯수
    private int startPage;            // 현재 페이지 목록에서 시작 페이지 번호 << 1 2 3 4 5 >> 라고 한다면 startPage = 1
    private int endPage;              // 현재 페이지 목록에서 끝 페이지 번호 << 1 2 3 4 5 >> 라고 한다면 endPage = 5
    private int firstPage;            // 처음 페이지 번호
    private int lastPage;             // 마지막 페이지 번호
    private int displayPageNum = 5;  // << 1, 2, 3, 4, 5 >>  페이지 목록에 몇 개씩 표시할건지 나타낼 변수
    private Criteria criteria;

    // [이전], [다음] 탭을 위한 필드.
    private int firstPageListNum;
    private int lastPageListNum;
    private int currentPageListNum;
    private int prevPageListNum;
    private int nextPageListNum;
    private int prevPage;
    private int nextPage;

    public PageMaker(Criteria criteria, int totalArticleCount) {
        this.criteria = criteria;
        this.totalArticleCount = totalArticleCount;

        setAllField();
    }

    /**
     * 모든 필드의 값을 세팅하기 위한 메서드.
     *
     * @return void
     */
    private void setAllField() {
        // 페이지 목록에서 첫 번째 페이지번호 && 마지막 페이지번호.
        endPage = (int) (Math.ceil(criteria.getPageNum() / (double) displayPageNum) * displayPageNum);
        startPage = (endPage - displayPageNum) + 1;

        // [처음] && [마지막] 탭의 페이지번호.
        firstPage = 1;
        lastPage = (int) (Math.ceil(totalArticleCount / (double) criteria.getAmount()));

        if (endPage > lastPage) endPage = lastPage;

        // [이전] && [다음] 탭을 구한다.
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
        lastPageListNum = (int) Math.ceil(lastPage / (double) displayPageNum);
        this.currentPageListNum = (int) Math.ceil(criteria.getPageNum() / (double) displayPageNum);

        // [이전] 탭을 눌렀을 때의 페이지 목록 값 && 페이지번호.
        if (currentPageListNum > firstPageListNum) {
            prevPageListNum = currentPageListNum - 1;
            prevPage = (prevPageListNum - 1) * displayPageNum + 1;
        }
        else {
            prevPageListNum = currentPageListNum;
            prevPage = firstPage;
        }

        // [다음] 탭을 눌렀을 때의 페이지 목록 값 && 페이지번호.
        if (currentPageListNum < lastPageListNum) {
            nextPageListNum = currentPageListNum + 1;
            nextPage = (nextPageListNum - 1) * displayPageNum + 1;
        }
        else {
            nextPageListNum = currentPageListNum;
            nextPage = lastPage;
        }
    }
}

