package com.pangtrue.practice.application.board.service;

import com.pangtrue.practice.application.board.domain.Board;
import com.pangtrue.practice.application.board.domain.PageMaker;
import org.springframework.data.domain.Pageable;

public interface BoardService {

    /**
     * 게시글 1개를 가져온다.
     *
     * @return Board
     */
    Board getArticle(Integer idx);

    /**
     * 전체 게시글의 리스트를 가져온다.
     *
     * @param pageable
     * @return PageMaker<Board>
     */
    PageMaker getArticleList(Pageable pageable);

    /**
     * 전체 게시글의 갯수를 가져온다.
     *
     * @return int
     */
    int getArticleCount();

    /**
     * 게시글 하나를 저장한다.
     *
     * @param board
     */
    int insertArticle(Board board);

    /**
     * 조회수를 +1 시킨다.
     *
     * @return void
     */
    void updateHits(Integer idx);

    /**
     * 게시글을 삭제한다.
     *
     * @param idx
     * @return
     */
    Integer deleteArticle(Integer idx);
}
