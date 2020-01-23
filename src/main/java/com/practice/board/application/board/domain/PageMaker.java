package com.practice.board.application.board.domain;

import lombok.Data;

/**
 *  페이지 목록을 처리하기 위한 클래스.
 *  페이지 목록 형식 : << 처음 이전 1 2 3 4 5 6 7 8 9 10 다음 마지막 >>
 */
@Data
public class PageMaker {

    private int totalArticleCount;    // 총 게시글의 갯수
    private int startPage;            // 지금 페이지 목록에서 시작 페이지 번호 << 1 2 3 4 5 >> 라고 한다면 startPage = 1
    private int endPage;              // 지금 페이지 목록에서 끝 페이지 번호 << 1 2 3 4 5 >> 라고 한다면 endPage = 5
    private int firstPage;            // 처음 페이지 번호
    private int lastPage;             // 마지막 페이지 번호
    private int displayPageNum = 5;  // << 1, 2, 3, 4, 5 >>  페이지 목록에 몇 개씩 표시할건지 나타낼 변수
    private Criteria criteria;

    // [이전], [다음] 탭을 위한 필드.
    private int firstPageGroupNum;
    private int lastPageGroupNum;
    private int currentPageGroupNum;
    private int prevPageGroupNum;
    private int nextPageGroupNum;
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
        // Math.ceil(현재 페이지 번호 / 페이지 목록에 보여줄 페이지 수) * 페이지 목록에 보여줄 페이지 수
        this.endPage = (int) (Math.ceil(criteria.getPageNum() / (double) displayPageNum) * displayPageNum);

        this.startPage = (endPage - displayPageNum) + 1;

        // 만일, 총 게시글의 갯수가 75개라면 endPage는 10이 아닌 8이 되어야 한다.
        // 이를 바탕으로 위에서 구한 endPage를 개선한다.
        // 총 게시글의 갯수를 가지고 끝 페이지가 몇 번이되는지 구한다.
        int realEndPage = (int) (Math.ceil(totalArticleCount / (double) criteria.getAmount()));

        if (endPage > realEndPage) {
            endPage = realEndPage;
        }

        // 첫 번째 페이지 번호와 마지막 페이지 번호.
        this.firstPage = 1;
        this.lastPage = (int) (Math.ceil(this.totalArticleCount / (double) criteria.getAmount()));

        // [이전]과 [다음]을 구한다.
        setPrevNext();
    }

    /**
     * [이전] [다음] 탭을 구하기 위한 메서드
     *
     * @return void
     */
    private void setPrevNext() {
        // 첫 번째 페이지그룹 번호와 마지막 페이지그룹 번호, 지금 보고있는 페이지의 페이지그룹 번호.
        this.firstPageGroupNum = 1;
        this.lastPageGroupNum = (int) Math.ceil(lastPage / (double) displayPageNum);
        this.currentPageGroupNum = (int) Math.ceil(criteria.getPageNum() / (double) displayPageNum);

        // IF (현재 페이지그룹 번호 > 첫 번째 페이지그룹 번호)
        //     이전 페이지그룹 번호 = 현재 페이지그룹 번호 - 1
        if (currentPageGroupNum > firstPageGroupNum) {
            prevPageGroupNum = currentPageGroupNum - 1;
        }
        else prevPageGroupNum = currentPageGroupNum;

        // IF (현재 페이지그룹 번호 < 마지막 페이지그룹 번호)
        //     현재 페이지그룹 번호 + 1
        if (currentPageGroupNum < lastPageGroupNum) {
            nextPageGroupNum = currentPageGroupNum + 1;
        }
        else nextPageGroupNum = currentPageGroupNum;


        // [이전] || [다음]을 클릭했을 때 가져야할 페이지번호.
        prevPage = (prevPageGroupNum - 1) * displayPageNum + 1;
        nextPage = (nextPageGroupNum - 1) * displayPageNum + 1;
    }
}

