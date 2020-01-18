package com.practice.board.application.board.service;

import com.practice.board.application.board.domain.Board;

import java.util.List;

public interface BoardService {

    /**
     * 게시글 1개를 가져온다.
     *
     * @return Board
     */
    Board getArticle();

    /**
     * 전체 게시글의 리스트를 가져온다.
     *
     * @return
     */
    List<Board> getArticleList();

    /**
     * 전체 게시글의 갯수를 가져온다.
     *
     * @return int
     */
    int getArticleCount();

}
