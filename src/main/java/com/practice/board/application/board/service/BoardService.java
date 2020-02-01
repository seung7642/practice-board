package com.practice.board.application.board.service;

import com.practice.board.application.board.domain.Board;
import com.practice.board.application.board.domain.Criteria;
import com.practice.board.application.board.domain.PageMaker;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
    PageMaker<Board> getArticleList(Pageable pageable);

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
}
