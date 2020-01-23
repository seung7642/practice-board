package com.practice.board.application.board.service;

import com.practice.board.application.board.domain.Board;
import com.practice.board.application.board.domain.Criteria;
import com.practice.board.application.board.domain.PageMaker;

import java.util.List;

public interface BoardService {

    /**
     * 게시글 1개를 가져온다.
     *
     * @return Board
     */
    Board getArticle(Integer idx) throws Exception;

    /**
     * 전체 게시글의 리스트를 가져온다.
     *
     * @return
     */
    List<Board> getArticleList() throws Exception;

    /**
     * 전체 게시글의 갯수를 가져온다.
     *
     * @return int
     */
    int getArticleCount() throws Exception;

    /**
     * 게시글 하나를 저장한다.
     *
     * @param board
     */
    void insertArticle(Board board) throws Exception;

    /**
     * 조회수를 +1 시킨다.
     *
     * @return void
     */
    void updateHits(Integer idx) throws Exception;

    /**
     * 페이징을 위한 PageMaker를 구한다.
     *
     * @param criteria
     * @return PageMaker
     */
    PageMaker getPageMaker(Criteria criteria) throws Exception;
}
